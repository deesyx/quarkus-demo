package org.acme.exception

import javax.ws.rs.core.Response.Status

class NotFoundException : BasicException {

    constructor(errorCode: ErrorCode) : super(errorCode.code, errorCode.message, Status.NOT_FOUND, null)

    constructor(errorCode: ErrorCode, cause: Throwable) : super(errorCode.code, errorCode.message, Status.NOT_FOUND, null, cause)

    constructor(errorCode: ErrorCode, data: Any) : super(errorCode.code, errorCode.message, Status.NOT_FOUND, data)

    constructor(errorCode: ErrorCode, data: Any, cause: Throwable) : super(errorCode.code, errorCode.message, Status.NOT_FOUND, data, cause)
}
