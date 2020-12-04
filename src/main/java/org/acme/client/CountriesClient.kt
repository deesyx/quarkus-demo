package org.acme.client

import org.acme.external.Country
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v2")
@RegisterRestClient(configKey = "country-api")
interface CountriesClient {
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: String): List<Country>
}
