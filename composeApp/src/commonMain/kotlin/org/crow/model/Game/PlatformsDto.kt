package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class PlatformsDto(
    private var windows: Boolean = false,
    private var mac:Boolean = false,
    private var linux:Boolean = false
)