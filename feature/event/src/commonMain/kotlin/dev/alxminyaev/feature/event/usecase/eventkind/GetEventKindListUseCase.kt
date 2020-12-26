package dev.alxminyaev.feature.event.usecase.eventkind

import dev.alxminyaev.feature.event.model.OutStudyEventKind
import dev.alxminyaev.feature.event.repository.OutStudyEventKindRepository

class GetEventKindListUseCase(
    private val eventKindRepository: OutStudyEventKindRepository
) {
    suspend fun invoke(): List<OutStudyEventKind> {
        return eventKindRepository.findAll()
    }

}