package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.model.OutStudyEvent

interface OutStudyEventRepository {

    suspend fun findBy(dataLimit: DataLimit): List<OutStudyEvent>

    suspend fun save(outStudyEvent: OutStudyEvent): Long
}