package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
class RoleDto(
    var id: Int=0,
    var name:ERole
) {
}