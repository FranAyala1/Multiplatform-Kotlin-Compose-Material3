package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class RecommendationsDto(
    private var total: Int
) {
}