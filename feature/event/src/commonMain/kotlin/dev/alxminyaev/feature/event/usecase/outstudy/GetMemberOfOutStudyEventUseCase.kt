package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.PermissionException
import dev.alxminyaev.feature.event.PaginationList
import dev.alxminyaev.feature.event.model.user.User
import dev.alxminyaev.feature.event.repository.MembersOutStudyEventRepository
import dev.alxminyaev.feature.event.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetMemberOfOutStudyEventUseCase(
    private val membersOutStudyEventRepository: MembersOutStudyEventRepository,
    private val isOrganizerUC: IsOrganizerOfOutStudyEventUseCase,
    private val userRepository: UserRepository
) {

    suspend fun invoke(forUserId: Long, outStudyEventId: Long): PaginationList<User> {
        val user = userRepository.findById(forUserId) ?: throw NotFoundException("Пользователь не найден")

        if (user.isAdmin && !isOrganizerUC.invoke(userId = user.id, outStudyEventId = outStudyEventId)) {
            throw PermissionException("Вы не являетесь организатором данного мероприятия")
        }

        return withContext(Dispatchers.Default) {
            val membersByEventId = async { membersOutStudyEventRepository.getMembersByEventId(outStudyEventId) }
            val size = async { membersOutStudyEventRepository.sizeBy(outStudyEventId) }
            PaginationList(
                size = size.await(),
                membersByEventId.await()
            )
        }
    }
}