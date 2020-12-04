package org.acme.exception

enum class ErrorCode constructor(val code: String, val message: String) {

    USER_NOT_FOUND("00001", "user not found"),
    GET_COUNTRY_FAILED("00002", "get country fail"),
    COUNTRY_NOT_VALID("00003", "country not valid")
}
