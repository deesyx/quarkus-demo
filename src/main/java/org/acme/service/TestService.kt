package org.acme.service

import org.acme.aspect.Logged
import javax.inject.Singleton

@Singleton
class TestService {

    fun test(): Any? {
        println("testService in testService:$this")
        val hello1 = hello1()
        val hello2 = hello2()
        val hello3 = hello3()
        val hello4 = hello4()
        return hello1 + hello2 + hello3 + hello4
    }

    @Logged
    fun hello1(): String {
        return "hello"
    }

    @Logged
    fun hello2(): String {
        return "world"
    }

    @Logged
    private fun hello3(): String {
        return "!"
    }

    fun hello4(): String {
        return "goodbye"
    }
}
