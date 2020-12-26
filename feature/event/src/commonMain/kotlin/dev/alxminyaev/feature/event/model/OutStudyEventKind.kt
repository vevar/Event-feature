package dev.alxminyaev.feature.event.model

import dev.alxminyaev.feature.event.api.models.EventKindOutStudyGetResponse


data class OutStudyEventKind(
    val id: Int,
    val name: String,
    val criteria: List<CriterionOutStudy>
)

fun OutStudyEventKind.toApi() = EventKindOutStudyGetResponse(
    id = id,
    name = name,
    criteria = criteria.map { it.toApi() }.toTypedArray()
)