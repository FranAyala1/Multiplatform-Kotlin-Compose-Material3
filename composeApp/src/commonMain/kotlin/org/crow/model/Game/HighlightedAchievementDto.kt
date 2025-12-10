package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class HighlightedAchievementDto(
    var name: String?,
    var path: String?
)