package dev.alxminyaev.feature.event.model

import com.soywiz.klock.DateTimeTz

data class StudyEvent(
    override val id: Long,
    override val name: String,
    override val address: String,
    override val description: String?,
    override val dateStart: DateTimeTz,
    override val dateEnd: DateTimeTz
) : Event() {
}