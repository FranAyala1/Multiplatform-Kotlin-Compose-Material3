package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
     var id: Long?,
     var description: String?
) {
}