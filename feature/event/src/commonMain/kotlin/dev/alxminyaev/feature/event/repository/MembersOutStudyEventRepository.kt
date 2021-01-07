package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.User

interface MembersOutStudyEventRepository {

    suspend fun addMember(outStudyEvent: OutStudyEvent, user: User)

}