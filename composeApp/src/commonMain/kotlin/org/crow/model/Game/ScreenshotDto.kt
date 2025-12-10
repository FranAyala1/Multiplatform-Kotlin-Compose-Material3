package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotDto(
    val pathThumbnail: String? = null,
    val pathFull: String? = null
) {
}