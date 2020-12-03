package org.acme.exception

import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ExceptionHandler : ExceptionMapper<Exception> {

    override fun toResponse(e: Exception): Response {
        return if (e is BasicException) {
            Response.status(e.httpStatus).entity(ErrorResponse.build(e)).build()
        } else {
            val serverException = ServerException("00000", e.localizedMessage)
            Response.status(Status.INTERNAL_SERVER_ERROR).entity(ErrorResponse.build(serverException)).build()
        }
    }
}
