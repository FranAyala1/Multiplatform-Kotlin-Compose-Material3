package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
     val status: String? = null,
     val message: String? = null,
     val data: T? = null
)