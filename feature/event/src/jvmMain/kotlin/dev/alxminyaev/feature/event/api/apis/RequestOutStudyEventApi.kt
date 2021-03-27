package dev.alxminyaev.feature.event.api.apis

import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.event.api.models.RequestOutStudyEventApi
import dev.alxminyaev.feature.event.model.outstudy.RequestOutStudyEvent
import dev.alxminyaev.feature.event.model.outstudy.toRequestListOutStudyEventResponse
import dev.alxminyaev.feature.event.usecase.outstudy.GetRequestsListByOutStudyEventUseCase
import dev.alxminyaev.feature.event.usecase.outstudy.PutRequestOutStudyEventUseCase
import dev.alxminyaev.tool.webServer.utils.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.RequestOutStudyEventApi() {
    authenticate {
        route("/api/v1/outstudy-event/{eventId}/request") {
            get {
                val eventId = call.parameters["eventId"]?.toLongOrNull() ?: throw ValidationDataException(
                    field = "eventId",
                    message = "eventId must be long"
                )
                val useCase by di().instance<GetRequestsListByOutStudyEventUseCase>()
                val user = call.user
                val list = useCase.invoke(eventId = eventId, userId = user.id)
                call.respond(list.toRequestListOutStudyEventResponse())
            }
        }
    }

    authenticate {
        route("/api/v1/outstudy-event/{eventId}/request/{requestId}") {
            put {
                val parameters = call.parameters
                val eventId =
                    parameters["eventId"]?.toLongOrNull()
                        ?: throw ValidationDataException(message = "eventId must be long")
                val requestId = parameters["requestId"]?.toLongOrNull()
                    ?: throw ValidationDataException(message = "requestId must be long")

                val useCase by di().instance<PutRequestOutStudyEventUseCase>()
                val body = call.receive<RequestOutStudyEventApi>()
                useCase.invoke(
                    userId = call.user.id,
                    eventId = eventId,
                    requestId = requestId,
                    status = RequestOutStudyEvent.Status.getById(body.status)
                )
                call.respond(HttpStatusCode.OK)
            }
        }
    }

}
