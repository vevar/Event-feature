package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent

interface RequestOutStudyEventRepository {

    suspend fun findByIdAndEventId(id: Long, eventId: Long): RequestOutStudyEvent?

    suspend fun findByEventAndUser(eventId: Long, userId: Long): RequestOutStudyEvent?

    suspend fun findAllByEventId(eventId: Long): List<RequestOutStudyEvent>

    suspend fun save(request: RequestOutStudyEvent): Long

    suspend fun update(request: RequestOutStudyEvent)

    suspend fun delete(id: Long)
}