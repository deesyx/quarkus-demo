package org.acme.resource.response

import org.acme.domain.Info

data class UserResponse(
        var id: Long? = null,
        var name: String? = null,
        var country: String? = null,
        var countryCode: String? = null,
        var countryCapital: String? = null,
        var info: Info? = null
)
