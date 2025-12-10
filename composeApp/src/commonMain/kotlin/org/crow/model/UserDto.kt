package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    var username:String="" ,
    var password:String=""
) {
}