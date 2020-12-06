package dev.alxminyaev.feature.event.rds

import dev.alxminyaev.feature.event.model.OutStudyEventKind

interface OutStudyEventKindRDS {

    suspend fun findById(id: Int): OutStudyEventKind?

    suspend fun save(entity: OutStudyEventKind): Int

    suspend fun save(entities: List<OutStudyEventKind>): List<Int>

    suspend fun update(entity: OutStudyEventKind)

    suspend fun delete(id: Int)

    suspend fun findAll(): List<OutStudyEventKind>

}