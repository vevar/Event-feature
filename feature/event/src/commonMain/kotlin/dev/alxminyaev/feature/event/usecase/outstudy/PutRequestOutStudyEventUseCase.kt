package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.repository.MembersOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.RequestOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository

class PutRequestOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository,
    private val membersOutStudyEventRepository: MembersOutStudyEventRepository,
    private val requestOutStudyEventRepository: RequestOutStudyEventRepository,
    private val userRepository: UserRepository
) {


    suspend fun invoke(userId: Long, eventId: Long, requestId: Long, status: RequestOutStudyEvent.Status) {

        val request = (requestOutStudyEventRepository.findByIdAndEventId(requestId, eventId)
            ?: throw NotFoundException("Запрос на участие не найден"))
        val outStudyEvent = outStudyEventRepository.findById(eventId)
            ?: throw NotFoundException("Мероприятие с eventId=${eventId} не найдено")

        val member =
            userRepository.findById(request.user.id) ?: throw NotFoundException("Пользователь с id=${request.user.id}")

        requestOutStudyEventRepository.update(request.copy(status = status))
        when (status) {
            RequestOutStudyEvent.Status.ACCEPT -> membersOutStudyEventRepository.addMember(outStudyEvent, member)
            RequestOutStudyEvent.Status.REJECT -> membersOutStudyEventRepository.deleteMember(outStudyEvent, member)
            RequestOutStudyEvent.Status.IN_PROCESS -> membersOutStudyEventRepository.deleteMember(outStudyEvent, member)
            else -> Unit
        }
    }
}