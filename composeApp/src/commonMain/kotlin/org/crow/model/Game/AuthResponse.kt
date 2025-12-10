package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token:String
) {
}