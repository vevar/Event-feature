package dev.alxminyaev.feature.event.model

import com.alxminyaev.tool.domain.model.EntityRef
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.parse
import dev.alxminyaev.feature.event.api.models.OrganizerResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventGetResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventPostRequest
import dev.alxminyaev.feature.event.model.user.User
import dev.alxminyaev.feature.event.model.user.toApi
import kotlinx.datetime.LocalDateTime

data class OutStudyEvent(
    override val id: Long,
    override val name: String,
    override val address: String,
    override val description: String?,
    override val dateStart: DateTimeTz,
    override val dateEnd: DateTimeTz,
    val dateRegistrationEnd: DateTimeTz?,
    val maxMembers: Int?,
    val minMembers: Int?,
    val isNeedMemberConfirmation: Boolean,
    val status: Status,
    val eventKind: EntityRef<Int, OutStudyEventKind>,
    val organizer: EntityRef<Long, User>
) : Event() {

    enum class Status(val id: Int) {
        UNKNOWN(0),
        DRAFT(1),
        CONFIRMATION(2),
        READY(3),
        CANCELED(4),
        IN_PROGRESS(5),
        FINISHED(6),
        NOT_READY(7);

        companion object {
            fun getById(id: Int): Status {
                return when (id) {
                    DRAFT.id -> DRAFT
                    CONFIRMATION.id -> CONFIRMATION
                    READY.id -> READY
                    CANCELED.id -> CANCELED
                    IN_PROGRESS.id -> IN_PROGRESS
                    FINISHED.id -> FINISHED
                    NOT_READY.id -> NOT_READY
                    else -> UNKNOWN
                }
            }
        }
    }
}

fun OutStudyEventPostRequest.toDomain(organizer: EntityRef<Long, User>): OutStudyEvent {
    val dateFormatter = DateFormat.invoke("yyyy-MM-dd'T'HH:mm")
    return OutStudyEvent(
        id = 0,
        name = name,
        address = address,
        description = description,
        dateStart = dateStart.let { dateFormatter.parse(it) },
        dateEnd = dateEnd.let { dateFormatter.parse(it) },
        dateRegistrationEnd = dateRegistrationEnd?.let { dateFormatter.parse(it) },
        maxMembers = maxMembers,
        minMembers = minMembers,
        isNeedMemberConfirmation = isNeedMemberConfirmation ?: false,
        status = OutStudyEvent.Status.DRAFT,
        organizer = organizer,
        eventKind = EntityRef(id = outstudyEventKindId)
    )
}

fun OutStudyEvent.toApi(): OutStudyEventGetResponse {
    return OutStudyEventGetResponse(
        id = id,
        name = name,
        address = address,
        description = description,
        dateStart = dateStart.toString(DateFormat.FORMAT1),
        dateEnd = dateEnd.toString(DateFormat.FORMAT1),
        dateRegistrationEnd = dateRegistrationEnd?.toString(DateFormat.FORMAT1),
        maxMembers = maxMembers,
        minMembers = minMembers,
        isNeedMemberConfirmation = isNeedMemberConfirmation ?: false,
        status = status.id,
        organizer = organizer.entity?.let { OrganizerResponse(it.toApi()) }
            ?: throw IllegalStateException("organizer must be set"),
        eventKindId = eventKind.id
    )
}