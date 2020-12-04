package org.acme.client

import org.acme.external.Country
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v2")
@RegisterRestClient(configKey = "country-api")
interface CountriesClient {
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam("name") name: String): List<Country>
}
