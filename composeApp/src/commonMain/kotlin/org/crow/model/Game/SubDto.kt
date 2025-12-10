package org.crow.model.Game

import kotlinx.serialization.Serializable

@Serializable
data class SubDto(
    private var packageid: Int?=0,
    private var percentSavingsText: String? ="",
    private var percentSavings:Int?=0,
    private var optionText: String?="",
    private var optionDescription: String?="" ,
    private var canGetFreeLicense: String?="",
    private var isFreeLicense:Boolean?=false,
    private var priceInCentsWithDiscount:Int?=0
) {
}