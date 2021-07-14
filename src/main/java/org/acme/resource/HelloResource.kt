package org.acme.resource

import java.time.LocalDateTime
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class HelloResource {

    @GET
    fun hello(): String {
        println("Thread: ${Thread.currentThread().name}. Start at: ${LocalDateTime.now()}.")
        Thread.sleep(1000L)
        println("Thread: ${Thread.currentThread().name}. End at: ${LocalDateTime.now()}")
        return "hello"
    }
}
