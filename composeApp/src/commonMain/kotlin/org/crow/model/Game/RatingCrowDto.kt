package org.crow.model.Game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingCrowDto(
     val id: Int? = null,
     val rating: Int? = null,
) {

}