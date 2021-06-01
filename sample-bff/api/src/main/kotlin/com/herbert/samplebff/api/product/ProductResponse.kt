package com.herbert.samplebff.api.product

data class ProductResponse(
    val id: String,
    val title: String,
    val description: String,
    val priceInCents: Int,
    val discount: Discount? = null
) {
    data class Discount(
        val percentage: Float,
        val valueInCents: Int
    )
}
