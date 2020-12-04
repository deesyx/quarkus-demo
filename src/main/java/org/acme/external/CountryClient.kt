package org.acme.external

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v2")
@RegisterRestClient(configKey = "country-api")
interface CountryClient {

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getByName(@PathParam name: String): List<Country>
}
