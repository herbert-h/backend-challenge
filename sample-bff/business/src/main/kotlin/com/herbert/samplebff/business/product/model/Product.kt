package com.herbert.samplebff.business.product.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID
import org.springframework.beans.factory.annotation.Value

@Table
data class Product(
    @Id
    val id: UUID? = null,
    val title: String,
    val description: String,
    val priceInCents: Int,
    val createdAt: Instant = Instant.now(),
    @Transient
    @Value("null") // https://github.com/spring-projects/spring-data-r2dbc/issues/449
    var discount: ProductDiscount? = null
)
