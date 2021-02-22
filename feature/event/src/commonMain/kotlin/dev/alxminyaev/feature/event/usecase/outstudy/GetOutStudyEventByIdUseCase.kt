package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository

class GetOutStudyEventByIdUseCase(
    private val outStudyRepository: OutStudyEventRepository
) {

    suspend fun invoke(eventId: Long): OutStudyEvent {
        return outStudyRepository.findById(eventId)
            ?: throw NotFoundException(message = "Событие по id=${eventId} не найдено")
    }
}