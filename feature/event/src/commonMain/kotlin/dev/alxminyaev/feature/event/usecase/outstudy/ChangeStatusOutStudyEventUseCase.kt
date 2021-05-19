package dev.alxminyaev.feature.event.usecase.outstudy

import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository

class ChangeStatusOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository
) {

    suspend fun invoke(id: Long, status: OutStudyEvent.Status) {
        outStudyEventRepository.updateStatus(id, status)
    }
}