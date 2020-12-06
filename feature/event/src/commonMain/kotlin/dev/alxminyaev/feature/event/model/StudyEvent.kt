package dev.alxminyaev.feature.event.model

import kotlinx.datetime.LocalDateTime

data class StudyEvent(
    override val id: Long,
    override val name: String,
    override val address: String,
    override val description: String?,
    override val dateStart: LocalDateTime,
    override val dateEnd: LocalDateTime
) : Event() {
}