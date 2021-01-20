package org.acme.service

import org.acme.aspect.Logged
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TestService {

    fun test(): Any? {
        return hello()
    }

    @Logged
    fun hello():String{
        return "hello"
    }
}
