package com.herbert.samplebff.business.product

import com.herbert.samplebff.business.product.model.Product
import com.herbert.samplebff.business.product.model.ProductDiscount
import java.util.UUID
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val discountGateway: DiscountGateway
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    suspend fun create(product: Product): Product {
        return productRepository.save(product)
            .also { logger.debug("New product created [productId=${product.id}]") }
    }

    suspend fun findById(id: UUID): Product {
        logger.debug("Recover product by id [productId=$id]")
        return productRepository.findById(id) ?:
            throw Exception() // TODO - Exception NotFound
    }

    suspend fun listWithDiscount(userId: String?): List<Product> {
        logger.debug("List products with discount [userId=$userId]")
        val products = productRepository.findAll()
        // UPGRADE - apply circuit breaker like <resilience4j>
        return products.map {
            applyDiscount(it, userId)
        }.toList()
    }

    private suspend fun applyDiscount(product: Product, userId: String?): Product =
        try {
            /*
                To save time and avoid creating a product repository on the rule engine
                And also that there are currently no rules that depend on the product specifically
                The grpc method will only return the discount percentage
                The discount amount is being calculated here
                But I think that as we passed the productId,
                The rule engine would be responsible for returning full discount object
             */
            val discountPercentage = discountGateway
                .calculateProductDiscountPercentage(productId = product.id.toString(), userId = userId)
            product.copy(
                discount = ProductDiscount(
                    percentage = discountPercentage,
                    valueInCents = discountPercentage.div(100)
                        .times(product.priceInCents).toInt()
                )
            )
        } catch (t: Throwable) {
            logger.warn("Could not get product discount. Message: ${t.message} [productId=${product.id}, userId=$userId]")
            product
        }
}
