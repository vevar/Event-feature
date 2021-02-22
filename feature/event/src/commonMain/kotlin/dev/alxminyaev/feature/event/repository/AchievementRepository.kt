package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.model.Achievement

interface AchievementRepository {

    suspend fun save(achievements: List<Achievement>)

    suspend fun findByUserAndEvent(userId: Long, eventId: Long): List<Achievement>

    suspend fun findByCriterionAndEvent(criterionId: Long, outStudyEventId: Long): List<Achievement>
}