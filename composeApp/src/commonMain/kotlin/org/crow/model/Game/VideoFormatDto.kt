package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class VideoFormatDto(
    var format480: String? = null,
    var max: String? = null
)