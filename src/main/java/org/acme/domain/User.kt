package org.acme.domain

import org.acme.resource.response.UserResponse

data class User(
        var id: Long? = null,
        var name: String? = null,
        var country: String? = null,
        var countryCode: String? = null,
        var countryCapital: String? = null,
        var info: Info? = null
) {

    fun toResponse(): UserResponse {
        return UserResponse(
                this.id, this.name, this.country, this.countryCode, this.countryCapital, this.info
        )
    }
}
