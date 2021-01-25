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


import com.google.gson.Gson
import dev.alxminyaev.feature.event.api.Paths
import dev.alxminyaev.feature.event.api.models.EntityIntCreatedResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventKindPostRequest
import dev.alxminyaev.feature.event.model.toApi
import dev.alxminyaev.feature.event.model.toDomain
import dev.alxminyaev.feature.event.usecase.eventkind.CreateEventKindUseCase
import dev.alxminyaev.feature.event.usecase.eventkind.GetEventKindListUseCase
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

@KtorExperimentalLocationsAPI
fun Route.OutStudyEventKindApi() {
    val gson = Gson()
    val empty = mutableMapOf<String, Any?>()

    delete<Paths.deleteByOutStudyEventKindId> { _: Paths.deleteByOutStudyEventKindId ->
        call.respond(HttpStatusCode.NotImplemented)

    }


    get<Paths.getOutStudyEventKinds> { _: Paths.getOutStudyEventKinds ->
        val useCase by di().instance<GetEventKindListUseCase>()
        val list = useCase.invoke()
        call.respond(list.map { it.toApi() })
    }

    authenticate {
        route("/api/v1/outstudy-eventkind") {
            post {
                val body = call.receive<OutStudyEventKindPostRequest>()
                val useCase by di().instance<CreateEventKindUseCase>()
                val id = useCase.invoke(name = body.name, criteria = body.criteria?.map { it.toDomain() })
                call.respond(EntityIntCreatedResponse(id))
            }
        }

    }

}
