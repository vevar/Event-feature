package dev.alxminyaev.feature.event.model.user

import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.api.models.ProfileApiModel
import kotlinx.serialization.Serializable


@Serializable
data class Profile(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
)

fun Profile.validate() {
    if (firstName.isBlank()) {
        throw ValidationDataException(field = "firstName", message = "Поле не должно быть пустым")
    }
    if (lastName.isBlank()) {
        throw ValidationDataException(field = "lastName", message = "Поле не должно быть пустым")
    }
}

fun Profile.toApi() = ProfileApiModel(
    firstName = firstName,
    lastName = lastName,
    middleName = middleName
)
