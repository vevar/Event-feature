package dev.alxminyaev.feature.event.model

import com.soywiz.klock.DateTimeTz

abstract class Event {
    abstract val id: Long
    abstract val name: String
    abstract val address: String
    abstract val description: String?
    abstract val dateStart: DateTimeTz
    abstract val dateEnd: DateTimeTz
}

