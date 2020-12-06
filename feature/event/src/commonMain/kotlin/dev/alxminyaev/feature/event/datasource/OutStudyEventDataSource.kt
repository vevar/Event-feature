package dev.alxminyaev.feature.event.datasource

import dev.alxminyaev.feature.event.rds.OutStudyEventRDS


interface OutStudyEventDataSource : OutStudyEventRDS {

    suspend fun deleteAll()
}