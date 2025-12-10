package org.crow.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewCrowDto(
     var id: Int = 0,
     var contenido: String? = null,
//    private var fecha: LocalDate
) {
}