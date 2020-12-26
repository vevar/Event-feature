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
package dev.alxminyaev.feature.event.api.models

import dev.alxminyaev.feature.event.api.models.OrganizerResponse

/**
 * 
 * @param id 
 * @param name 
 * @param address 
 * @param outstudyEventKind 
 * @param status 
 * @param isNeedMemberConfirmation 
 * @param organizer 
 * @param description 
 * @param dateStart UTC
 * @param dateEnd UTC
 * @param dateRegistrationEnd UTC
 * @param maxMembers 
 * @param minMembers 
 * @param isConfirmed 
 */
data class OutStudyEventGetResponse (
    val id: kotlin.Long,
    val name: kotlin.String,
    val address: kotlin.String,
    val outstudyEventKind: kotlin.Int,
    val status: kotlin.Int,
    val isNeedMemberConfirmation: kotlin.Boolean,
    val organizer: OrganizerResponse,
    val description: kotlin.String? = null,
    /* UTC */
    val dateStart: java.time.LocalDateTime? = null,
    /* UTC */
    val dateEnd: java.time.LocalDateTime? = null,
    /* UTC */
    val dateRegistrationEnd: java.time.LocalDateTime? = null,
    val maxMembers: kotlin.Int? = null,
    val minMembers: kotlin.Int? = null,
    val isConfirmed: kotlin.Boolean? = null
) 
