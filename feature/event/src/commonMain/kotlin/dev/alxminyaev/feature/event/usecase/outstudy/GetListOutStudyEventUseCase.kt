package dev.alxminyaev.feature.event.usecase.outstudy

import com.soywiz.klock.DateTimeTz
import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository

class GetListOutStudyEventUseCase(
    private val outStudyRepository: OutStudyEventRepository
) {


    suspend fun invoke(
        dataLimit: DataLimit,
        dateStart: DateTimeTz?,
        dateEnd: DateTimeTz?,
        status: OutStudyEvent.Status?,
        organizerId: Long?,
        memberId: Long?
    ): List<OutStudyEvent> {
        return outStudyRepository.findBy(dataLimit, dateStart, dateEnd, status, organizerId, memberId)
    }
}