package org.acme.exception

import javax.ws.rs.core.Response

class ServerException : BasicException {
    constructor(errorCode: ErrorCode) : super(errorCode.code, errorCode.message, Response.Status.INTERNAL_SERVER_ERROR, null)

    constructor(errorCode: ErrorCode, cause: Throwable) : super(errorCode.code, errorCode.message, Response.Status.INTERNAL_SERVER_ERROR, null, cause)

    constructor(errorCode: ErrorCode, data: Any) : super(errorCode.code, errorCode.message, Response.Status.INTERNAL_SERVER_ERROR, data)

    constructor(errorCode: ErrorCode, data: Any, cause: Throwable) : super(errorCode.code, errorCode.message, Response.Status.INTERNAL_SERVER_ERROR, data, cause)

    constructor(code: String, message: String) : super(code, message, Response.Status.INTERNAL_SERVER_ERROR, null)

    constructor(code: String, message: String, data: Any) : super(code, message, Response.Status.INTERNAL_SERVER_ERROR, data)

    constructor(code: String, message: String, data: Any, cause: Throwable) : super(code, message, Response.Status.INTERNAL_SERVER_ERROR, data, cause)

}
