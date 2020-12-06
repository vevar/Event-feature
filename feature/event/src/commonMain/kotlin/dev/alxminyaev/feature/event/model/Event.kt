package dev.alxminyaev.feature.event.model

import kotlinx.datetime.LocalDateTime

abstract class Event {
    abstract val id: Long
    abstract val name: String
    abstract val address: String
    abstract val description: String?
    abstract val dateStart: LocalDateTime
    abstract val dateEnd: LocalDateTime
}

