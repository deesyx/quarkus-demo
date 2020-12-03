package org.acme.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.lang.Exception

@JsonInclude(Include.NON_NULL)
class ErrorResponse(
        var code: String?,
        var message: String?,
        var data: Any?
) {
    companion object {

        fun build(basicException: BasicException): ErrorResponse {
            return ErrorResponse(basicException.code, basicException.message, basicException.data)
        }
    }
}
