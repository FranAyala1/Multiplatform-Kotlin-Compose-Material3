package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class PriceOverviewDto(
    private var currency: String? = null,
    private var initial: Int = 0,
    private var finalPrice: Int = 0,
    private var discountPercent: Int = 0,
    private var initialFormatted: String? = null,
    private var finalFormatted: String? = null
) {
}