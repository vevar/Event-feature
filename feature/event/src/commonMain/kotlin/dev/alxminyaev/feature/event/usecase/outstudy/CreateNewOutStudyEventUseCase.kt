package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import kotlinx.datetime.LocalDateTime

class CreateNewOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository
) {

    suspend fun invoke(
        name: String,
        address: String,
        description: String? = null,
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime? = null,
        dateRegistrationEnd: LocalDateTime? = null,
        maxMembers: Int? = null,
        minMembers: Int? = null,
        eventKindId: Int,
        isNeedMemberConfirmation: Boolean? = null
    ) {
        if (name.isBlank()){
            throw ValidationDataException("name", "Название не может быть пустым")
        }
        if (address.isBlank()){
            throw ValidationDataException("address", "Место провидение должно быть задано")
        }

    }
}