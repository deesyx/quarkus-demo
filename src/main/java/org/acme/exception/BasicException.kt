package org.acme.exception

import javax.ws.rs.core.Response.Status

open class BasicException : RuntimeException {

    var code: String? = null
    final override var message: String? = null
    var httpStatus: Status? = null
    var data: Any? = null

    constructor(code: String, message: String, httpStatus: Status, data: Any?) : super(message) {
        this.code = code
        this.message = message
        this.httpStatus = httpStatus
        this.data = data
    }

    constructor(code: String, message: String, httpStatus: Status, data: Any?,
                cause: Throwable?) : super(message, cause) {
        this.code = code
        this.message = message
        this.httpStatus = httpStatus
        this.data = data
    }
}
