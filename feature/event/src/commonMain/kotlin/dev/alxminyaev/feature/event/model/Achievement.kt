package dev.alxminyaev.feature.event.model

import com.alxminyaev.tool.domain.model.EntityRef
import dev.alxminyaev.feature.event.model.user.User

data class Achievement(
    val id: Long,
    val user: EntityRef<Long, User>,
    val event: EntityRef<Long, OutStudyEvent>,
    val criterion: EntityRef<Long, CriterionOutStudy>
)