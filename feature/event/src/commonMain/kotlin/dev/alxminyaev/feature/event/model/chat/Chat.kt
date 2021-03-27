package dev.alxminyaev.feature.event.model.chat

data class Chat(
    val id: Long,
    val name: String,
    val type: Int = 2
)
