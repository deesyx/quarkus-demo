package org.acme.resource

import org.acme.service.TestService
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TestResource {

    @Inject
    lateinit var testService: TestService

    @GET
    fun test(): Any? {
        println("testService in testResource:$testService")
        return testService.test()
    }
}
