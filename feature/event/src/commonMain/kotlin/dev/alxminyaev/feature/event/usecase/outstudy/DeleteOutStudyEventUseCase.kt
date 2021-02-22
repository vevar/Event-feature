package dev.alxminyaev.feature.event.usecase.outstudy

import dev.alxminyaev.feature.event.repository.OutStudyEventRepository

class DeleteOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository
) {

    suspend fun invoke(eventId: Long) {
        outStudyEventRepository.delete(eventId)
    }
}