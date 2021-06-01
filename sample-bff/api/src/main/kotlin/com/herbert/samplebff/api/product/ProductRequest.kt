package com.herbert.samplebff.api.product

data class ProductRequest(
    val title: String,
    val description: String,
    val priceInCents: Int
)
