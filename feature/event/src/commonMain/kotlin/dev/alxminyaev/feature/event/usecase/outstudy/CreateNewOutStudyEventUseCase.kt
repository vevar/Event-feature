package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.error.exceptions.PermissionException
import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.chat.Chat
import dev.alxminyaev.feature.event.model.user.Role
import dev.alxminyaev.feature.event.repository.ChatRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventKindRepository
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository

class CreateNewOutStudyEventUseCase(
    private val outStudyEventRepository: OutStudyEventRepository,
    private val eventKindRepository: OutStudyEventKindRepository,
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
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

        if (outStudyEvent.dateStart >= outStudyEvent.dateEnd) {
            throw ValidationDataException("dateEnd", "Дата окончания должна быть позже, чем дата начала")
        }
        val eventKind = eventKindRepository.findById(outStudyEvent.eventKind.id)
            ?: throw ValidationDataException(message = "Тип события не найден")
        val organizers = userRepository.findByIds(outStudyEvent.organizer.map { it.id })
        if (organizers.isEmpty()) {
            throw ValidationDataException(message = "Список организаторов не может быть пустым")
        }
        organizers.forEach {
            if (!it.roles.contains(Role.OUT_STUDY_ORGANIZER)) {
                throw PermissionException("У пользователя с id=${it.id} нет прав организатора")
            }
        }

        val chat = Chat(
            id = 0,
            name = "Чат мероприятия ${outStudyEvent.name.toLowerCase()}",
        )

        val chatId = chatRepository.save(chat)

        return outStudyEventRepository.save(
            outStudyEvent.copy(
                eventKind = EntityRef(eventKind.id, eventKind),
                organizer = organizers.map { EntityRef(it.id, it) },
                chat = chat.copy(id = chatId)
            )
        )
    }
}