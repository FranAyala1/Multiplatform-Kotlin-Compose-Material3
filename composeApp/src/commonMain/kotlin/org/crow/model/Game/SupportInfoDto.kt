package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class SupportInfoDto(
    private var url: String? = null,
    private var email: String? = null
) {
}