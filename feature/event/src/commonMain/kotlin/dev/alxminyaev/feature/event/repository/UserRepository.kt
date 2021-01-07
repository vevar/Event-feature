package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.model.user.User

interface UserRepository {

    suspend fun findById(id: Long): User?
}