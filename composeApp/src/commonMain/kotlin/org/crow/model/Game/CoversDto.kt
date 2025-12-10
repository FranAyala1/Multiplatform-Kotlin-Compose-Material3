package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class CoversDto(
    val id: Long? = null,
    val largeCover: List<CoverDto>? = null,
    val normalCover: List<CoverDto>? = null
)