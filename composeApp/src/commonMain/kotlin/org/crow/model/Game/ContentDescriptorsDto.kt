package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class ContentDescriptorsDto(
    private var ids: List<Int?>? = null,
    private var notes: String? = null
) {
}