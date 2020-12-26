package dev.alxminyaev.feature.event.model

import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.api.models.OutStudyEventCriteria
import dev.alxminyaev.feature.event.api.models.OutStudyEventCriterionPostRequest

enum class CriterionType(val id: Int) {
    UNKNOWN(0),
    All(1),
    BY_RATING_PLACE(2),
    CUSTOM(3);

    companion object {
        fun getBy(id: Int): CriterionType {
            return when (id) {
                All.id -> All
                BY_RATING_PLACE.id -> BY_RATING_PLACE
                CUSTOM.id -> CUSTOM
                else -> UNKNOWN
            }
        }
    }
}

sealed class CriterionOutStudy(
    open val id: Long,
    open val name: String,
    open val value: Int
) {

    abstract val type: CriterionType

    data class All(
        override val id: Long,
        override val name: String,
        override val value: Int
    ) : CriterionOutStudy(id, name, value) {

        override val type: CriterionType = CriterionType.All
    }

    data class ByRatingPlace(
        override val id: Long,
        override val name: String,
        override val value: Int,
        val bottomPlace: Int,
        val topPlace: Int
    ) : CriterionOutStudy(id, name, value) {

        override val type: CriterionType = CriterionType.BY_RATING_PLACE
    }

    data class Custom(
        override val id: Long,
        override val name: String,
        override val value: Int
    ) : CriterionOutStudy(id, name, value) {
        override val type: CriterionType = CriterionType.CUSTOM
    }
}


fun OutStudyEventCriterionPostRequest.toDomain(): CriterionOutStudy {
    return when (typeId) {
        CriterionType.All.id -> CriterionOutStudy.All(
            id = 0,
            name = "За участие",
            value = value,
        )
        CriterionType.BY_RATING_PLACE.id -> CriterionOutStudy.ByRatingPlace(
            id = 0,
            name = "C $topPlace по $bottomPlace",
            value = value,
            bottomPlace = bottomPlace ?: throw ValidationDataException(
                "bottomPlace",
                "Укажите корректный диапазон, например c 1 по 10"
            ),
            topPlace = topPlace ?: throw ValidationDataException(
                "bottomPlace",
                "Укажите корректный диапазон, например c 1 по 10"
            )
        )
        CriterionType.CUSTOM.id -> CriterionOutStudy.All(
            id = 0,
            name = name ?: throw ValidationDataException("name", "Название не должно быть пустым"),
            value = value,
        )
        else -> throw ValidationDataException("type", "Неизвестный тип")
    }
}

fun CriterionOutStudy.toApi(): OutStudyEventCriteria {
    return when (this) {
        is CriterionOutStudy.All -> {
            OutStudyEventCriteria(
                id = id,
                name = name,
                value = value,
                typeId = type.id
            )
        }
        is CriterionOutStudy.ByRatingPlace -> {
            OutStudyEventCriteria(
                id = id,
                name = name,
                value = value,
                typeId = type.id,
                bottomPlace = bottomPlace,
                topPlace = topPlace
            )
        }
        is CriterionOutStudy.Custom -> {
            OutStudyEventCriteria(
                id = id,
                name = name,
                value = value,
                typeId = type.id
            )
        }
    }
}