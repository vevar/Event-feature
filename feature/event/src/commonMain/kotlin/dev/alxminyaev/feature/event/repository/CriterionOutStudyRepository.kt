package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.model.CriterionOutStudy

interface CriterionOutStudyRepository {

    suspend fun save(entities: List<CriterionOutStudy>): List<CriterionOutStudy>
}