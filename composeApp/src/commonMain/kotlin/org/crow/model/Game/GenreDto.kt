package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    private var id: Int,
    private var description: String?
) {
}