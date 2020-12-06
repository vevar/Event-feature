package dev.alxminyaev.feature.event.model

import kotlinx.datetime.LocalDateTime

data class OutStudyEvent(
    override val id: Long,
    override val name: String,
    override val address: String,
    override val description: String?,
    override val dateStart: LocalDateTime,
    override val dateEnd: LocalDateTime,
    val dateRegistrationEnd: LocalDateTime?,
    val maxMembers: Int,
    val minMembers: Int,
    val isNeedMemberConfirmation: Boolean,
    val status: Status,
    val eventKind: OutStudyEventKind
) : Event() {

    enum class Status(val id: Int) {
        DRAFT(1),
        CONFIRMATION(2),
        READY(3),
        CANCELED(4),
        IN_PROGRESS(5),
        FINISHED(6),
        NOT_READY(7)
    }
}