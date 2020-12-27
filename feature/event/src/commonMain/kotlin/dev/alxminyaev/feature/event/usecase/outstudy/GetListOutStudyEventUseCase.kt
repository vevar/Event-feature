package dev.alxminyaev.feature.event.usecase.outstudy

import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository

class GetListOutStudyEventUseCase(
    private val outStudyRepository: OutStudyEventRepository
) {


    suspend fun invoke(dateLimit: DataLimit): List<OutStudyEvent> {
        return outStudyRepository.findBy(dateLimit)
    }
}