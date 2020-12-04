package org.acme.domain

import org.acme.resource.response.UserResponse

class User {

    var id: Long? = null
    var name: String? = null
    var country: String? = null
    var countryCode: String? = null
    var countryCapital: String? = null

    fun toResponse(): UserResponse {
        return UserResponse(this.id, this.name, this.country, this.countryCode, this.countryCapital)
    }
}
