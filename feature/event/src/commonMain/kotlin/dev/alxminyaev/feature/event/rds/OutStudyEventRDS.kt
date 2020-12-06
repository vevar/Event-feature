package dev.alxminyaev.feature.event.rds

import dev.alxminyaev.feature.event.model.OutStudyEvent

interface OutStudyEventRDS {

    suspend fun findById(id: Long): OutStudyEvent?

    suspend fun save(entity: OutStudyEvent): Long

    suspend fun save(entities: List<OutStudyEvent>): List<Long>

    suspend fun update(entity: OutStudyEvent)

    suspend fun delete(id: Long)

    suspend fun findAll(dataLimit: DataLimit): List<OutStudyEvent>

    class DataLimit(
        val size: Int,
        val offset: Long
    )
}