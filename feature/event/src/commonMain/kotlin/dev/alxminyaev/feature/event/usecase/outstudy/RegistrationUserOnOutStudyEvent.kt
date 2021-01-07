package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.PermissionException
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.repository.MembersOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.RequestOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository

class RegistrationUserOnOutStudyEvent(
    private val userRepository: UserRepository,
    private val outStudyEventRepository: OutStudyEventRepository,
    private val membersOutStudyEventRepository: MembersOutStudyEventRepository,
    private val requestOutStudyEventRepository: RequestOutStudyEventRepository
) {

    suspend fun invoke(eventId: Long, userId: Long) {
        val user = userRepository.findById(userId) ?: throw NotFoundException("Пользователь с id=${userId} не найден")
        val outStudyEvent =
            outStudyEventRepository.findById(eventId) ?: throw  NotFoundException("Событие с id=${eventId} не найдено")
        if (outStudyEvent.maxMembers != null && outStudyEvent.sizeMembers >= outStudyEvent.maxMembers) {
            throw PermissionException("На событие не могут зарегестрироваться больше, чем ${outStudyEvent.maxMembers} участников")
        }
        if (outStudyEvent.isNeedMemberConfirmation) {
            val memberRequest = requestOutStudyEventRepository.findByEventAndUser(eventId, userId)
            if (memberRequest != null) {
                when (memberRequest.status) {
                    RequestOutStudyEvent.Status.ACCEPT -> {
                        membersOutStudyEventRepository.addMember(outStudyEvent, user)
                        return
                    }
                    RequestOutStudyEvent.Status.IN_PROCESS -> {
                        return
                    }
                    RequestOutStudyEvent.Status.REJECT -> {
                        throw PermissionException("Вам отказано в участие")
                    }
                    else -> return
                }
            } else {
                val newRequest = RequestOutStudyEvent(
                    id = 0,
                    event = EntityRef(outStudyEvent.id, outStudyEvent),
                    user = EntityRef(user.id, user),
                    status = RequestOutStudyEvent.Status.IN_PROCESS
                )
                requestOutStudyEventRepository.save(newRequest)
            }
        } else {
            membersOutStudyEventRepository.addMember(outStudyEvent, user)
        }
    }

}