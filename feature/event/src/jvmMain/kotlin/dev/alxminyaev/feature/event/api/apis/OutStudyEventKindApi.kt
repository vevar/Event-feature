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
import io.ktor.application.call
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authentication
import io.ktor.auth.authenticate
import io.ktor.auth.OAuthAccessTokenResponse
import io.ktor.auth.OAuthServerSettings
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.delete
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route

import dev.alxminyaev.feature.event.api.Paths
import dev.alxminyaev.feature.event.api.infrastructure.ApiPrincipal


import dev.alxminyaev.feature.event.api.models.EntityIntCreatedResponse
import dev.alxminyaev.feature.event.api.models.ErrorResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventKindListGetResponse
import dev.alxminyaev.feature.event.api.models.OutStudyEventKindPostRequest

@KtorExperimentalLocationsAPI
fun Route.OutStudyEventKindApi() {
    val gson = Gson()
    val empty = mutableMapOf<String, Any?>()

    delete<Paths.outStudydeleteEventKindById> {  _: Paths.outStudydeleteEventKindById ->
        call.respond(HttpStatusCode.NotImplemented)

    }


    get<Paths.outStudygetEventKinds> {  _: Paths.outStudygetEventKinds ->
        val exampleContentType = "application/json"
val exampleContentString = """{
          "data" : [ {
            "name" : "name",
            "id" : 0
          }, {
            "name" : "name",
            "id" : 0
          } ]
        }"""

when(exampleContentType) {
    "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
    "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
    else -> call.respondText(exampleContentString)
}

    }


    route("/api/v1/outstudy-eventkind") {
        post {
            val exampleContentType = "application/json"
val exampleContentString = """{
              "id" : 0
            }"""

when(exampleContentType) {
    "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
    "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
    else -> call.respondText(exampleContentString)
}

        }
    }

}
