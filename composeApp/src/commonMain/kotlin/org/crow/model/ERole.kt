package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
enum class ERole {
    ROLE_USER,
    ROLE_ADMIN
}