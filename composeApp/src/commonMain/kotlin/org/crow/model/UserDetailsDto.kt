package org.crow.model

import androidx.compose.ui.semantics.Role
import kotlinx.serialization.Serializable
import org.crow.model.Game.RatingCrowDto

@Serializable
data class UserDetailsDto(
    var id: Int=0,
    var username: String="",
    var password: String="",
    var roles: Set<RoleDto> = emptySet(),
    var ratings: Set<RatingCrowDto>?=null,
    var reviews: Set<ReviewCrowDto>?=null
)