package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class AchievementsDto(
    var total: Int = 0,
    var highlighted: List<org.crow.model.Game.HighlightedAchievementDto>? = null
)