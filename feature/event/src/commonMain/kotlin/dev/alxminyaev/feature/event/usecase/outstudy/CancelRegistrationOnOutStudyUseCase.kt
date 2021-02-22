package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.PermissionException
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.repository.MembersOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.RequestOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository

class CancelRegistrationOnOutStudyUseCase(
    private val userRepository: UserRepository,
    private val outStudyEventRepository: OutStudyEventRepository,
    private val requestOutStudyEventRepository: RequestOutStudyEventRepository,
    private val membersOutStudyEventRepository: MembersOutStudyEventRepository
) {

    suspend fun invoke(eventId: Long, userId: Long) {
        val user = userRepository.findById(userId) ?: throw NotFoundException("Пользователь с id=${userId} не найден")

        val outStudyEvent =
            outStudyEventRepository.findById(eventId) ?: throw  NotFoundException("Событие с id=${eventId} не найдено")


        if (outStudyEvent.isNeedMemberConfirmation) {
            val memberRequest = requestOutStudyEventRepository.findByEventAndUser(eventId, userId)
                ?: throw NotFoundException("Запрос на участие для пользователя (id=${userId}) в мероприятии (id=${eventId}) не найден ")
            when (memberRequest.status) {
                RequestOutStudyEvent.Status.ACCEPT -> {
                    membersOutStudyEventRepository.deleteMember(outStudyEvent, user)
                    return
                }
                RequestOutStudyEvent.Status.IN_PROCESS -> {
                    requestOutStudyEventRepository.delete(memberRequest.id)
                    return
                }
                RequestOutStudyEvent.Status.REJECT -> {
                    throw PermissionException("Вам отказано в участие")
                }
                else -> return
            }
        } else {
            membersOutStudyEventRepository.deleteMember(outStudyEvent, user)
        }
    }
}