package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.event.model.Achievement
import dev.alxminyaev.feature.event.repository.CriterionOutStudyRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.AchievementRepository
import dev.alxminyaev.feature.event.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class RewardMembersInOutStudyEventUseCase(
    private val eventRepository: OutStudyEventRepository,
    private val criterionOutStudyRepository: CriterionOutStudyRepository,
    private val achievementRepository: AchievementRepository,
    private val userRepository: UserRepository
) {

    suspend fun invoke(eventId: Long, criteriaUsers: Map<Long, List<Long>>) {
        withContext(Dispatchers.Default) {
            val outStudyEvent =
                eventRepository.findById(eventId) ?: throw  NotFoundException("Событие с id=${eventId} не найдено")
            criteriaUsers.map { criterionUsers ->
                async {
                    val criterionOutStudy = (criterionOutStudyRepository.findById(criterionUsers.key)
                        ?: throw NotFoundException("Критерий с id=${criterionUsers.key} не найден"))

                    val rewards = userRepository.findByIds(criterionUsers.value).map { user ->
                        val criterionAchievements = achievementRepository.findByCriterionAndEvent(
                            criterionId = criterionOutStudy.id,
                            outStudyEventId = outStudyEvent.id
                        )

                        if (criterionAchievements.find { it.user.id == user.id } != null) {
                            null
                        } else {
                            Achievement(
                                id = 0,
                                user = EntityRef(id = user.id, entity = user),
                                event = EntityRef(id = eventId, entity = outStudyEvent),
                                criterion = EntityRef(id = criterionOutStudy.id, criterionOutStudy)
                            )
                        }
                    }

                    achievementRepository.save(rewards.filterNotNull())
                }
            }.awaitAll()
        }
    }
}