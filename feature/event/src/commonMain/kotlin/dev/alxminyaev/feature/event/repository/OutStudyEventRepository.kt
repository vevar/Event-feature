package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.User

interface OutStudyEventRepository {

    suspend fun findById(id: Long): OutStudyEvent?

    suspend fun findBy(dataLimit: DataLimit): List<OutStudyEvent>

    suspend fun save(outStudyEvent: OutStudyEvent): Long

}