package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.model.chat.Chat
import dev.alxminyaev.feature.event.model.user.User

interface ChatRepository {

    suspend fun save(chat: Chat): Long

    suspend fun addUserToChat(chat: Chat, user: User)

    suspend fun removeUserFromChat(chat: Chat, user: User)
}