package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.PermissionException
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.RequestOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository

class GetRequestsListByOutStudyEventUseCase(
    private val userRepository: UserRepository,
    private val eventRepository: OutStudyEventRepository,
    private val requestOutStudyEventRepository: RequestOutStudyEventRepository
) {

    suspend fun invoke(eventId: Long, userId: Long): List<RequestOutStudyEvent> {
        val user =
            userRepository.findById(userId) ?: throw PermissionException("Пользователь c userId=${userId} не найден")
        if (user.isAdmin.not()) {
            val outStudyEvent =
                eventRepository.findById(eventId)
                    ?: throw NotFoundException("Мероприятие с eventId=${eventId} не найдено")
            if (outStudyEvent.organizer.find { it.id == userId } == null) {
                throw PermissionException("Вы не являетесь организатором мероприятия")
            }
        }
        return requestOutStudyEventRepository.findAllByEventId(eventId)
    }
}