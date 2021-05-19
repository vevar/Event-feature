package dev.alxminyaev.feature.event.repository

import com.soywiz.klock.DateTimeTz
import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.User

interface OutStudyEventRepository {

    suspend fun findById(id: Long): OutStudyEvent?

    suspend fun findBy(
        dataLimit: DataLimit,
        dateStart: DateTimeTz?,
        dateEnd: DateTimeTz?,
        status: OutStudyEvent.Status?,
        organizerId: Long?,
        memberId: Long?
    ): List<OutStudyEvent>

    suspend fun save(outStudyEvent: OutStudyEvent): Long

    suspend fun delete(id: Long)


    suspend fun sizeBy(
        dataLimit: DataLimit,
        dateStart: DateTimeTz?,
        dateEnd: DateTimeTz?,
        status: OutStudyEvent.Status?,
        organizerId: Long?,
        memberId: Long?
    ): Long
}