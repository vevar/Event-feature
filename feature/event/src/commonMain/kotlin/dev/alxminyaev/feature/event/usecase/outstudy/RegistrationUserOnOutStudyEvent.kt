package dev.alxminyaev.feature.event.usecase.outstudy

import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.PermissionException
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.model.user.Role
import dev.alxminyaev.feature.event.model.user.User
import dev.alxminyaev.feature.event.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class RegistrationUserOnOutStudyEvent(
    private val userRepository: UserRepository,
    private val outStudyEventRepository: OutStudyEventRepository,
    private val membersOutStudyEventRepository: MembersOutStudyEventRepository,
    private val chatRepository: ChatRepository,
    private val requestOutStudyEventRepository: RequestOutStudyEventRepository
) {

    suspend fun invoke(eventId: Long, userId: Long) {
        withContext(Dispatchers.Default) {
            val userJob =
                async {
                    val user = userRepository.findById(userId)
                        ?: throw NotFoundException("Пользователь с id=${userId} не найден")

                    if (!user.roles.contains(Role.OUT_STUDY_MEMBER)) {
                        throw PermissionException("У вас нет прав участника для внеучебных мероприятий")
                    }
                    user
                }

            val outStudyEventJob =
                async {
                    val outStudyEvent = (outStudyEventRepository.findById(eventId)
                        ?: throw  NotFoundException("Событие с id=${eventId} не найдено"))
                    if (outStudyEvent.maxMembers != null && outStudyEvent.sizeMembers >= outStudyEvent.maxMembers) {
                        throw PermissionException("На событие не могут зарегестрироваться больше, чем ${outStudyEvent.maxMembers} участников")
                    }
                    outStudyEvent
                }
            val outStudyEvent = outStudyEventJob.await()
            val user = userJob.await()

            if (outStudyEvent.isNeedMemberConfirmation) {
                val memberRequest = requestOutStudyEventRepository.findByEventAndUser(eventId, userId)
                if (memberRequest != null) {
                    when (memberRequest.status) {
                        RequestOutStudyEvent.Status.ACCEPT -> {
                            addUserToEvent(outStudyEvent, user)
                            return@withContext
                        }
                        RequestOutStudyEvent.Status.IN_PROCESS -> {
                            return@withContext
                        }
                        RequestOutStudyEvent.Status.REJECT -> {
                            throw PermissionException("Вам отказано в участие")
                        }
                        else -> return@withContext
                    }
                } else {
                    val newRequest = RequestOutStudyEvent(
                        id = 0,
                        event = EntityRef(outStudyEvent.id, outStudyEvent),
                        user = EntityRef(user.id, user),
                        status = RequestOutStudyEvent.Status.IN_PROCESS
                    )
                    requestOutStudyEventRepository.save(newRequest)
                }
            } else {
                addUserToEvent(outStudyEvent, user)
            }
        }
    }


    private suspend fun addUserToEvent(outStudyEvent: OutStudyEvent, user: User) {
        withContext(Dispatchers.Default) {
            val memberToAdd = async { membersOutStudyEventRepository.addMember(outStudyEvent, user) }
            val addToChat = async { chatRepository.addUserToChat(outStudyEvent.chat, user) }
            addToChat.await()
            memberToAdd.await()
        }
    }
}