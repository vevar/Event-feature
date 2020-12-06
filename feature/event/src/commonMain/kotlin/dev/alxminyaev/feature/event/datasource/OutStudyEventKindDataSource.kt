package dev.alxminyaev.feature.event.datasource

import dev.alxminyaev.feature.event.rds.OutStudyEventKindRDS


interface OutStudyEventKindDataSource : OutStudyEventKindRDS {

    suspend fun deleteAll()

}