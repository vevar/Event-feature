package dev.alxminyaev.feature.event.model.user

import dev.alxminyaev.feature.event.api.models.UserApiEntity

data class User(
    val id: Long,
    val profile: Profile,
    val roles: List<Role>
)

fun User.toApi()= UserApiEntity(
    id = id,
    profile= profile.toApi(),
    roles = roles.map { it.id }.toTypedArray()
)