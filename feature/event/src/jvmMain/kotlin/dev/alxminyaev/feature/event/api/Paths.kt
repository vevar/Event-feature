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
package dev.alxminyaev.feature.event.api

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location

object Paths {
    /**
     * Get outstudy-event by id (Not implemented, MEDIUM Priority)
     * 
     * @param id ID of object to delete 
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-event/{id}") class deleteEventByOutStudyEventId(val id: kotlin.Long)

    /**
     * Deleting of user registration in outstudy-event (Not implemented, HIGH Priority)
     * 
     * @param id ID of outstudy-event 
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-event/{id}/registration") class deleteRegistrationByOutStudyEventId(val id: kotlin.Long)

    /**
     * Get members of outstudy-event by id (Not implemented, LOW Priority)
     * 
     * @param id ID of object to return 
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-event/{id}/members") class getByOutStudyEventId(val id: kotlin.Long)

    /**
     * Get outstudy-event by id (Not implemented, LOW Priority)
     * 
     * @param id ID of object to return 
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-event/{id}") class getMembersOfOutStudyEventById(val id: kotlin.Long)

    /**
     * Get list of  outstudy-events 
     * 
     * @param offset The number of items to skip before starting to collect the result set 
     * @param limit The numbers of items to return 
     * @param dateStart  (optional)
     * @param dateEnd  (optional)
     * @param status  (optional)
     * @param isConfirmed  (optional)
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-event") class getOutStudyEvents(val offset: kotlin.Long, val limit: kotlin.Int, val dateStart: java.time.LocalDateTime? = null, val dateEnd: java.time.LocalDateTime? = null, val status: kotlin.Long? = null, val isConfirmed: kotlin.Boolean? = null)

    /**
     * OutStudyEvent kind by id (Not implemented, MEDIUM Priority)
     * 
     * @param id ID of outstudy-event 
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-eventkind/{id}") class deleteByOutStudyEventKindId(val id: kotlin.Long)

    /**
     * Get list of OutStudyEventKinds
     * 
     */
    @KtorExperimentalLocationsAPI
    @Location("/api/v1/outstudy-eventkind") class getOutStudyEventKinds()

}
