package org.acme.aspect

import javax.annotation.Priority
import javax.interceptor.AroundInvoke
import javax.interceptor.Interceptor
import javax.interceptor.InvocationContext

@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
class LoggingInterceptor {

    @AroundInvoke
    fun logInvocation(context: InvocationContext): Any? {
        println("start-method: ${context.method.name}")
        val ret = context.proceed()
        println("end-result: $ret")
        return ret
    }
}
