package com.herbert.samplebff.business.product

interface DiscountGateway {
    suspend fun calculateProductDiscountPercentage(productId: String, userId: String?): Float
}
