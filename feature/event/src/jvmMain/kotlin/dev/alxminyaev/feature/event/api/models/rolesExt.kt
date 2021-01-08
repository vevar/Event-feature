package dev.alxminyaev.feature.event.api.models

import dev.alxminyaev.feature.event.model.user.Role
import dev.alxminyaev.tool.webServer.utils.User


fun User.isAdmin() = rolesId.find { Role.ADMIN.id == it } != null