package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.api.models.OutStudyEventUserTopBoardResponse
import dev.alxminyaev.feature.event.api.models.PutOutStudyEventUserTopBoardRequest
import dev.alxminyaev.feature.event.api.models.PutUserPlaceTopBoardRequest
import dev.alxminyaev.feature.event.model.outstudy.OutStudyEventUserTopBoard

interface OutStudyUserTopBoardRepository {

    suspend fun findByEventId(id: Long): OutStudyEventUserTopBoardResponse?

    suspend fun updateByEventId(outStudyEventUserTopBoard: PutOutStudyEventUserTopBoardRequest, eventId: Long)
}