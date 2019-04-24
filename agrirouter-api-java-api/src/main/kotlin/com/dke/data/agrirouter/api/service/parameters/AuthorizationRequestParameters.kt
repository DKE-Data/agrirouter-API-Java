package com.dke.data.agrirouter.api.service.parameters

import com.dke.data.agrirouter.api.enums.SecuredOnboardingResponseType
import com.dke.data.agrirouter.api.service.ParameterValidation
import com.dke.data.agrirouter.api.service.parameters.container.DynamicAttributesStore
import lombok.ToString

/**
 * Parameters class. Encapsulation for the services.
 */
@ToString
class AuthorizationRequestParameters : DynamicAttributesStore(), ParameterValidation {

    lateinit var applicationId : String

    var redirectUri: String = ""

    var state: String = ""

    var responseType: SecuredOnboardingResponseType = SecuredOnboardingResponseType.ONBOARD

}