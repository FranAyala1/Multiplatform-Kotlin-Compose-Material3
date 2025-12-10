package org.crow.model

import kotlinx.serialization.Serializable
import org.crow.model.Game.RatingCrowDto

@Serializable
data class ReviewCrowGames(
    var reviewCrow:ReviewCrowDto ,
    var ratingCrow:RatingCrowDto ,
    var url: String ,
    var username :String) {
}