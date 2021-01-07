package dev.alxminyaev.feature.event.model.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import dev.alxminyaev.feature.event.api.models.RequestOutStudyEventApi
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.User

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


