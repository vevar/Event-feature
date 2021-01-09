package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.error.exceptions.PermissionException
import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.user.Role
import dev.alxminyaev.feature.event.repository.OutStudyEventKindRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository
import kotlinx.datetime.LocalDateTime

class CreateNewOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository,
    private val eventKindRepository: OutStudyEventKindRepository,
    private val userRepository: UserRepository
) {

    suspend fun invoke(
        outStudyEvent: OutStudyEvent
    ): Long {

        if (outStudyEvent.name.isBlank()) {
            throw ValidationDataException("name", "Название не может быть пустым")
        }
        if (outStudyEvent.address.isBlank()) {
            throw ValidationDataException("address", "Место провидение должно быть задано")
        }

        if (outStudyEvent.dateStart.utc <= outStudyEvent.dateEnd.utc) {
            throw ValidationDataException("dateEnd", "Дата окончания должна быть позже, чем дата начала")
        }
        val eventKind = eventKindRepository.findById(outStudyEvent.eventKind.id)
            ?: throw ValidationDataException(message = "Тип события не найден")
        val organizer = userRepository.findById(outStudyEvent.organizer.id)
            ?: throw ValidationDataException("Организатор не найден")
        if (!organizer.roles.contains(Role.OUT_STUDY_ORGANIZER)) {
            throw PermissionException("Для создания мероприятия нужны права организатора")
        }

        return outStudyEventRepository.save(
            outStudyEvent.copy(
                eventKind = EntityRef(eventKind.id, eventKind),
                organizer = EntityRef(organizer.id, organizer)
            )
        )
    }
}