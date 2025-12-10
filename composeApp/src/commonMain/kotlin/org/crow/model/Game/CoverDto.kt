package org.crow.model.Game

import kotlinx.serialization.Serializable
import org.crow.model.UserDto

@Serializable
data class CoverDto(
     var id: Int = 0,
     var url: String? = null,
     var thumb: String?=null
) {
}