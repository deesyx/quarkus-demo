package org.acme.resource

import org.eclipse.microprofile.context.ManagedExecutor
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/thread")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ThreadResource {

    @Inject
    lateinit var exec: ManagedExecutor

    @GET
    @Path("/test")
    fun test() {
        println(Thread.currentThread().name)
        println(Thread.activeCount())
        exec.execute { println(Thread.currentThread().name) }
        println(Thread.activeCount())
        println(exec.toString())
    }
}
