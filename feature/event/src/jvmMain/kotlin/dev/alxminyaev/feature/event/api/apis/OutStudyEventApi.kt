/**
 * Event service
 * Event service
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: alxminyaev@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package dev.alxminyaev.feature.event.api.apis


import com.alxminyaev.tool.domain.model.EntityRef
import com.alxminyaev.tool.domain.util.dateFormat
import com.alxminyaev.tool.domain.util.toMppDateTime
import com.alxminyaev.tool.error.exceptions.UnauthorizedException
import com.alxminyaev.tool.error.exceptions.ValidationDataException
import com.google.gson.Gson
import com.soywiz.klock.parse
import dev.alxminyaev.feature.event.DataLimit
import dev.alxminyaev.feature.event.api.Paths
import dev.alxminyaev.feature.event.api.models.*
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.model.toApi
import dev.alxminyaev.feature.event.model.toDomain
import dev.alxminyaev.feature.event.repository.OutStudyEventRepository
import dev.alxminyaev.feature.event.usecase.outstudy.*
import dev.alxminyaev.tool.webServer.utils.User
import dev.alxminyaev.tool.webServer.utils.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async
import org.kodein.di.instance
import org.kodein.di.ktor.di

@KtorExperimentalLocationsAPI
fun Route.OutStudyEventApi() {
    val gson = Gson()
    val empty = mutableMapOf<String, Any?>()


    authenticate {
        delete<Paths.deleteEventByOutStudyEventId> { param: Paths.deleteEventByOutStudyEventId ->
            val useCase by di().instance<DeleteOutStudyEventUseCase>()
            useCase.invoke(param.id)
            call.respond(HttpStatusCode.OK)
        }
    }

    authenticate {
        delete<Paths.deleteRegistrationByOutStudyEventId> { param: Paths.deleteRegistrationByOutStudyEventId ->
            val useCase by di().instance<CancelRegistrationOnOutStudyUseCase>()
            useCase.invoke(eventId = param.id, userId = call.user.id)
            call.respond(HttpStatusCode.OK)
        }
    }

    authenticate {
        get<Paths.getByOutStudyEventId> { param: Paths.getByOutStudyEventId ->
            val user = call.principal<User>() ?: throw UnauthorizedException()
            val getOutStudyEventUC by di().instance<GetOutStudyEventByIdUseCase>()
            val outStudyEvent = getOutStudyEventUC.invoke(eventId = param.id)
            call.respond(outStudyEvent.toApi())
        }

    }

    authenticate {
        get<Paths.getMembersOfOutStudyEventById> { param: Paths.getMembersOfOutStudyEventById ->
            val user = call.principal<User>() ?: throw UnauthorizedException()
            val getMemberUC by di().instance<GetMemberOfOutStudyEventUseCase>()
            val members = getMemberUC.invoke(
                forUserId = user.id,
                outStudyEventId = param.id,
                dataLimit = DataLimit(offset = param.offset, size = param.limit)
            )
            call.respond(members)
        }
    }


    get<Paths.getOutStudyEvents> { param: Paths.getOutStudyEvents ->
        val useCase by di().instance<GetListOutStudyEventUseCase>()
        val sizeRequest by di().instance<OutStudyEventRepository>()
        val dataLimit = DataLimit(offset = param.offset, size = param.limit)

        val dateStart = param.dateStart?.let { dateFormat.parse(it) }
        val dateEnd = param.dateEnd?.let { dateFormat.parse(it) }
        val status = param.status?.let { OutStudyEvent.Status.getById(it) }

        val data = async {
            useCase.invoke(
                dataLimit,
                dateStart = dateStart,
                dateEnd = dateEnd,
                status = status,
                organizerId = param.organizerId,
                memberId = param.memberId
            ).map { it.toApi() }
        }
        val totalDataSize = async {
            sizeRequest.sizeBy(
                dataLimit,
                dateStart = dateStart,
                dateEnd = dateEnd,
                status = status,
                organizerId = param.organizerId,
                memberId = param.memberId
            )
        }
        call.respond(OutStudyEventListResponse(size = totalDataSize.await(), data = data.await().toTypedArray()))
    }


    authenticate {
        route("/api/v1/outstudy-event") {
            post {
                val obj = call.receive<OutStudyEventPostRequest>()
                val user = call.principal<User>() ?: throw UnauthorizedException()
                val useCase by di().instance<CreateNewOutStudyEventUseCase>()
                val eventId = useCase.invoke(obj.toDomain(organizers = listOf(EntityRef(user.id))))
                call.respond(EntityLongCreatedResponse(eventId))
            }
        }

    }

    authenticate {
        route("/api/v1/outstudy-event/{id}/registration") {
            post {
                val eventId = call.parameters["id"]!!.toLong()
                val user = call.user ?: throw  UnauthorizedException()
                val useCase by di().instance<RegistrationUserOnOutStudyEvent>()
                useCase.invoke(eventId = eventId, userId = user.id)
                call.respond(HttpStatusCode.OK)
            }

        }
    }

    authenticate {
        route("/api/v1/outstudy-event/{id}") {
            put {
                call.respond(HttpStatusCode.NotImplemented)

            }
        }

    }

    authenticate {
        route("/api/v1/out-study-event/{id}/status") {
            put {
                val id = call.parameters["id"]?.toLongOrNull() ?: throw ValidationDataException()
                val body = call.receive<ChangeStatusOfOutStudyEventRequest>()
                val useCase by di().instance<ChangeStatusOutStudyEventUseCase>()

                useCase.invoke(id, OutStudyEvent.Status.getById(body.status))

                call.respond(HttpStatusCode.OK)
            }
        }
    }


    authenticate {
        route("/api/v1/outstudy-event/{eventId}/reward") {
            post {
                val eventId = call.parameters["eventId"]!!.toLong()
                val useCase by di().instance<RewardMembersInOutStudyEventUseCase>()
                val body = call.receive<RewardsListCriterionUsersRequest>()
                useCase.invoke(
                    eventId = eventId,
                    criteriaUsers = mapOf(*(body.data.map { it.criterionId to it.usersIds.toList() }.toTypedArray()))
                )
                call.respond(HttpStatusCode.OK)
            }
        }
    }

}
