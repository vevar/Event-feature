package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.repository.RequestOutStudyEventRepository

class PutRequestOutStudyEventUseCase(
    private val requestOutStudyEventRepository: RequestOutStudyEventRepository
) {


    suspend fun invoke(eventId: Long, requestId: Long, status: RequestOutStudyEvent.Status) {
        val request = (requestOutStudyEventRepository.findByIdAndEventId(requestId, eventId)
            ?: throw NotFoundException("Запрос на участие не найден"))

        requestOutStudyEventRepository.update(request.copy(status = status))
    }
}