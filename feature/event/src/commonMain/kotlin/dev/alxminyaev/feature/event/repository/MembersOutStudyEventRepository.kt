package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.User

interface MembersOutStudyEventRepository {

    suspend fun addMember(outStudyEvent: OutStudyEvent, user: User)

    suspend fun deleteMember(outStudyEvent: OutStudyEvent, user: User)

    suspend fun findByOutStudyEventId(outStudyEventId: Long, dataLimit: DataLimit): List<User>

    suspend fun sizeBy(outStudyEventId: Long): Long
}