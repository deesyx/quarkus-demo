package org.acme.resource

import org.acme.service.TestDataService
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/test-data")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TestDataResource {

    @Inject
    lateinit var testDataService: TestDataService

    @POST
    fun test(){
        testDataService.test()
    }
}
