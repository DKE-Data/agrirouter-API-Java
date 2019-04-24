package com.dke.data.agrirouter.api.service.parameters

import com.dke.data.agrirouter.api.service.ParameterValidation
import com.dke.data.agrirouter.api.service.parameters.container.DynamicAttributesContainer
import com.google.protobuf.ByteString
import lombok.ToString
import javax.validation.constraints.NotNull

/**
 * Parameters class. Encapsulation for the services.
 */
@ToString
class PayloadParameters : DynamicAttributesContainer(), ParameterValidation {

    @NotNull
    var typeUrl: String = ""

    @NotNull
    lateinit var value: ByteString

}