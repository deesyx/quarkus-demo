package org.acme.resource.response

data class UserResponse(
        var id: Long?,
        var name: String?,
        var country: String?,
        var countryCode: String?,
        var countryCapital: String?
)
