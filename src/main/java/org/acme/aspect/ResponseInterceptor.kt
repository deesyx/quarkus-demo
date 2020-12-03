package org.acme.aspect

import javax.annotation.Priority
import javax.interceptor.AroundInvoke
import javax.interceptor.Interceptor
import javax.interceptor.InvocationContext
import javax.ws.rs.core.Response

@Interceptor
@WrapResponse
class ResponseInterceptor {

    @AroundInvoke
    @Throws(Exception::class)
    fun wrapResponse(invocationContext: InvocationContext): Any {
        val response = invocationContext.proceed()
        if (response is Response) {
            return response
        }
        val annotation = invocationContext.method.getAnnotation(WrapResponse::class.java)
        val httpStatus = annotation.httpStatus
        return Response.status(httpStatus).entity(response).build()
    }

}
