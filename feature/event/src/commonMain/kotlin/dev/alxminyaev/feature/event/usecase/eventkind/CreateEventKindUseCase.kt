package dev.alxminyaev.feature.event.usecase.eventkind

import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.model.CriterionOutStudy
import dev.alxminyaev.feature.event.model.CriterionType
import dev.alxminyaev.feature.event.model.OutStudyEventKind
import dev.alxminyaev.feature.event.repository.OutStudyEventKindRepository

class CreateEventKindUseCase(
    private val outStudyEventKindRepository: OutStudyEventKindRepository,
) {

    suspend fun invoke(name: String, criteria: List<CriterionOutStudy>?): Int {
        criteria?.forEach {
            if (it.name.isBlank()) {
                throw ValidationDataException("criteria", "У всех критериев должно быть задано имя")
            }
            if (it.value <= 0) {
                throw ValidationDataException("criteria", "У всех критериев должно быть значение балла > 0")
            }
            if (it.type == CriterionType.UNKNOWN) {
                throw ValidationDataException("criteria", "Неизвестный тип критерия")
            }
        }
        if (name.isBlank()) {
            throw ValidationDataException("name", "Название типа мероприятия не должно быть пустым")
        }

        val outStudyEventKind = OutStudyEventKind(
            id = 0,
            name = name,
            criteria = criteria ?: listOf()
        )

        return outStudyEventKindRepository.save(outStudyEventKind)
    }


}