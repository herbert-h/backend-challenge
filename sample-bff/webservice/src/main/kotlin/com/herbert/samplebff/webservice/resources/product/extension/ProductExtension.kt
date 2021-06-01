package com.herbert.samplebff.webservice.resources.product.extension

import com.herbert.samplebff.api.product.ProductRequest
import com.herbert.samplebff.api.product.ProductResponse
import com.herbert.samplebff.business.product.model.Product
import com.herbert.samplebff.business.product.model.ProductDiscount

fun ProductRequest.toEntity() = Product(
    title = title,
    description = description,
    priceInCents = priceInCents
)

fun Product.toResponse() = ProductResponse(
    id = id?.toString() ?: throw Exception(), //TODO - create exception
    title = title,
    description = description,
    priceInCents = priceInCents,
    discount = discount?.toResponse()
)

private fun ProductDiscount.toResponse() = ProductResponse.Discount(
    percentage = percentage,
    valueInCents = valueInCents
)
