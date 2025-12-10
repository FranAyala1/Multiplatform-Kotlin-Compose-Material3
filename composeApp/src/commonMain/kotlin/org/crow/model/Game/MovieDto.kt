package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    private var idSteam: Int?=0,
    private var name: String?,
    private var thumbnail: String?,
    private var webm: org.crow.model.Game.VideoFormatDto?=VideoFormatDto(),
    private var mp4: org.crow.model.Game.VideoFormatDto?=VideoFormatDto(),
    private var highlight:Boolean?
) {
}