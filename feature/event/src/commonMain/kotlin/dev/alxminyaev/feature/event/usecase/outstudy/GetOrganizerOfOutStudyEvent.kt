package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.event.model.user.User
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository

class GetOrganizerOfOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository,
    private val userRepository: UserRepository
) {

    suspend fun invoke(outStudyEventId: Long): User {
        val outStudyEvent =
            outStudyEventRepository.findById(outStudyEventId) ?: throw NotFoundException("Мероприятие не найдено")

        val organizer = outStudyEvent.organizer
        return organizer.entity ?: userRepository.findById(organizer.id)
        ?: throw NotFoundException("Организатор не найден")
    }

}

class IsOrganizerOfOutStudyEventUseCase(
    private val getOrganizerOfOutStudyEventUseCase: GetOrganizerOfOutStudyEventUseCase
) {
    suspend fun invoke(userId: Long, outStudyEventId: Long): Boolean {
        return getOrganizerOfOutStudyEventUseCase.invoke(outStudyEventId).id == userId
    }
}