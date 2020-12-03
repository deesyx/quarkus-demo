package org.acme.domain

import org.acme.resource.response.UserResponse

class User(
        var id: Long?,
        var name: String
) {
    fun toResponse(): UserResponse {
        return UserResponse(this.id, this.name)
    }
}
