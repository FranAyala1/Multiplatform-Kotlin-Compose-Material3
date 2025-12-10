package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDateDto(
     var comingSoon: Boolean=false,
     var date: String?=""
) {
}