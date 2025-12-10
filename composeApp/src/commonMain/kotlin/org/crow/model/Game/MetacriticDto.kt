package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class MetacriticDto(
     var score: Int = 0,
     var url: String? = null
) {
}