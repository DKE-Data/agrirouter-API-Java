package com.dke.data.agrirouter.api.service.parameters

import com.dke.data.agrirouter.api.service.ParameterValidation
import com.dke.data.agrirouter.api.service.parameters.base.AbstractParameterBase
import com.google.protobuf.ByteString
import javax.validation.constraints.NotNull

/**
 * Parameters class. Encapsulation for the services.
 */
class PayloadParameters : AbstractParameterBase(), ParameterValidation {

    @NotNull
    var typeUrl: String = ""

    @NotNull
    lateinit var value: ByteString

}