package org.acme.exception

import javax.ws.rs.core.Response.Status

class BadRequestException : BasicException {

    constructor(errorCode: ErrorCode) : super(errorCode.code, errorCode.message, Status.BAD_REQUEST, null)

    constructor(errorCode: ErrorCode, cause: Throwable) : super(errorCode.code, errorCode.message, Status.BAD_REQUEST, null, cause)

    constructor(errorCode: ErrorCode, data: Any) : super(errorCode.code, errorCode.message, Status.BAD_REQUEST, data)

    constructor(errorCode: ErrorCode, data: Any, cause: Throwable) : super(errorCode.code, errorCode.message, Status.BAD_REQUEST, data, cause)
}
