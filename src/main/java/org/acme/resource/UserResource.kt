package org.acme.resource

import org.acme.domain.User
import org.acme.resource.response.UserResponse
import org.acme.service.UserService
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserResource {

    @Inject
    lateinit var userService: UserService

    @GET
    @Path("/{name}")
    fun getUser(@PathParam("name") name: String): UserResponse {
        return userService.getUser(name).toResponse()
    }
}
