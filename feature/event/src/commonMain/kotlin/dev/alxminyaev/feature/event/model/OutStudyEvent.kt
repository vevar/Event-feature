package dev.alxminyaev.feature.event.model

import com.alxminyaev.tool.domain.model.EntityRef
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.parse
import dev.alxminyaev.feature.event.api.models.ChatResponse
import dev.alxminyaev.feature.event.api.models.OrganizerResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventGetResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventPostRequest
import dev.alxminyaev.feature.event.model.chat.Chat
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
    val chat: Chat,
    val dateRegistrationEnd: DateTimeTz?,
    val maxMembers: Int?,
    val minMembers: Int?,
    val isNeedMemberConfirmation: Boolean,
    val status: Status,
    val eventKind: EntityRef<Int, OutStudyEventKind>,
    val organizer: List<EntityRef<Long, User>>,
    val sizeMembers: Long
) : Event() {

    enum class Status(val id: Int) {
        NEW(10),
        IN_PROGRESS(20),
        FINISHED(30),
        IN_ARCHIVE(40);

        companion object {

            fun getById(id: Int): Status {
                return when (id) {
                    NEW.id -> NEW
                    IN_PROGRESS.id -> IN_PROGRESS
                    FINISHED.id -> FINISHED
                    IN_ARCHIVE.id -> IN_ARCHIVE
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }
}

fun OutStudyEventPostRequest.toDomain(organizers: List<EntityRef<Long, User>>): OutStudyEvent {
    val dateFormatter = DateFormat.invoke("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
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
        status = OutStudyEvent.Status.NEW,
        organizer = organizers,
        eventKind = EntityRef(id = outstudyEventKindId),
        sizeMembers = 0,
        chat = Chat(
            id = 0,
            name = "Чат мероприятия $name"
        )
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
        organizer = organizer.map {
            it.entity?.let { OrganizerResponse(it.toApi()) }
                ?: throw IllegalStateException("organizer must be set")
        }.first(),
        eventKindId = eventKind.id,
        chat = ChatResponse(
            id = chat.id,
            name = chat.name,
            type = chat.type
        )
    )
}

