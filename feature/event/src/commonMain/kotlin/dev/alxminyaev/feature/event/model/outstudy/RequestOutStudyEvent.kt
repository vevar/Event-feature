package dev.alxminyaev.feature.event.model.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import dev.alxminyaev.feature.event.api.models.RequestListOutStudyEventResponse
import dev.alxminyaev.feature.event.api.models.RequestOutStudyEventApi
import dev.alxminyaev.feature.event.api.models.RequestOutStudyResponse
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.User
import dev.alxminyaev.feature.event.model.user.toApi

data class RequestOutStudyEvent(
    val id: Long,
    val user: EntityRef<Long, User>,
    val event: EntityRef<Long, OutStudyEvent>,
    val status: Status
) {
    enum class Status(val id: Int) {
        UNKNOWN(0),
        IN_PROCESS(1),
        ACCEPT(2),
        REJECT(3);

        companion object {
            fun getById(id: Int): Status {
                return when (id) {
                    IN_PROCESS.id -> IN_PROCESS
                    ACCEPT.id -> ACCEPT
                    REJECT.id -> REJECT
                    else -> UNKNOWN
                }
            }
        }
    }
}

fun RequestOutStudyEvent.toRequestOutStudyResponse(): RequestOutStudyResponse {
    return RequestOutStudyResponse(
        id = id,
        status = status.id,
        outStudyEventId = event.id,
        user = user.entity?.toApi() ?: throw IllegalStateException("user must be set")
    )
}

fun List<RequestOutStudyEvent>.toRequestListOutStudyEventResponse(): RequestListOutStudyEventResponse {
    return RequestListOutStudyEventResponse(
        data = this.map { it.toRequestOutStudyResponse() }.toTypedArray()
    )
}
