package dev.alxminyaev.feature.event

import dev.alxminyaev.feature.event.api.apis.OutStudyEventApi
import dev.alxminyaev.feature.event.api.apis.OutStudyEventKindApi
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Application.eventFeatureRouting() {
    routing {
        route("/event") {
            OutStudyEventApi()
            OutStudyEventKindApi()
        }
    }

}
