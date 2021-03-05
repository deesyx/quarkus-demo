package org.acme.exception

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
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
