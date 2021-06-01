package com.herbert.samplebff.business.product

import com.herbert.samplebff.business.product.model.Product
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProductRepository: CoroutineCrudRepository<Product, UUID>