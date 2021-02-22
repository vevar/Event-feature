package dev.alxminyaev.feature.event.usecase.eventkind

import dev.alxminyaev.feature.event.repository.OutStudyEventKindRepository

class DeleteEventKindUseCase(
    private val outStudyEventKindRepository: OutStudyEventKindRepository
) {

    suspend fun invoke(id: Int) {
        outStudyEventKindRepository.delete(id)
    }
}