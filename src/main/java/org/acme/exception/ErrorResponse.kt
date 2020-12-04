package org.acme.exception

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
