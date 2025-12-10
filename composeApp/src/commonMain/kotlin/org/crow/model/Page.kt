package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Long,
    val size: Int,
    val number: Int,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean
) {
    val isLast: Boolean get() = last
}