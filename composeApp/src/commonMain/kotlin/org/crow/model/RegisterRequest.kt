package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    var username: String = "",
    var email: String = "",
    var password: String = ""
) {
}