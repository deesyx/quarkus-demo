package org.acme.exception

enum class ErrorCode constructor(val code: String, val message: String) {

    USER_NOT_FOUND("00001", "user not found")
}
