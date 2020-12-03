package org.acme.aspect

import java.lang.annotation.Inherited
import javax.interceptor.InterceptorBinding
import javax.ws.rs.core.Response.Status

@Inherited
@InterceptorBinding
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapResponse(val httpStatus: Status = Status.OK)
