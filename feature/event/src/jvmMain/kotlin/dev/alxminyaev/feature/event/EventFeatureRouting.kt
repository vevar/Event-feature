package dev.alxminyaev.feature.event

import dev.alxminyaev.feature.event.api.apis.OutStudyEventApi
import dev.alxminyaev.feature.event.api.apis.OutStudyEventKindApi
import dev.alxminyaev.feature.event.api.apis.RequestOutStudyEventApi
import dev.alxminyaev.feature.event.api.apis.TopBoardApi
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Application.eventFeatureRouting() {
    routing {
        route("/event") {
            RequestOutStudyEventApi()
            OutStudyEventApi()
            OutStudyEventKindApi()
            TopBoardApi()
        }
    }

}
