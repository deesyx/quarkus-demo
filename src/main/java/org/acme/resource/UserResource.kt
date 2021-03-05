package org.acme.resource

import org.acme.resource.response.UserResponse
import org.acme.service.UserService
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserResource {

    @Inject
    lateinit var userService: UserService

    @POST
    fun addUser(userAddRequest: UserAddRequest): UserResponse {
        val user = userService.addUser(userAddRequest)
        return user.toResponse()
    }

    @GET
    @Path("/{name}")
    fun getUser(@PathParam("name") name: String): UserResponse {
        return userService.getUser(name).toResponse()
    }
}
