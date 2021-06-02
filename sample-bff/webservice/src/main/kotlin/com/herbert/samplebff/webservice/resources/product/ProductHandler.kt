package com.herbert.samplebff.webservice.resources.product

import com.herbert.samplebff.api.product.ProductRequest
import com.herbert.samplebff.business.product.ProductService
import com.herbert.samplebff.webservice.resources.product.extension.toEntity
import com.herbert.samplebff.webservice.resources.product.extension.toResponse
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class ProductHandler(
    val productService: ProductService
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(ProductHandler::class.java)
    }

    suspend fun createProduct(request: ServerRequest): ServerResponse {
        logger.debug("Receive request to create new product")
        val body: ProductRequest = request.awaitBody()
        val product = productService.create(body.toEntity())
        return ServerResponse
            .status(HttpStatus.CREATED)
            .bodyValueAndAwait(product.toResponse())
            .also { logger.info("Reply ${it.rawStatusCode()} for create product with productId=${product.id}") }
    }

    suspend fun getProduct(request: ServerRequest): ServerResponse {
        logger.debug("Receive request to get product")
        val productId = request.pathVariable("product-id")
        val product = productService.findById(UUID.fromString( productId))
        return ServerResponse
            .status(HttpStatus.OK)
            .bodyValueAndAwait(product.toResponse())
            .also { logger.info("Reply ${it.rawStatusCode()} for get product with productId=${product.id}") }
    }

    suspend fun listProducts(request: ServerRequest): ServerResponse {
        logger.debug("Receive request to list products")
        /*
            The use of "X-" it's not officially recommended anymore, link for RFC explains bellow
            https://stackoverflow.com/a/3561399
         */
        val userId = request.headers()
            .firstHeader("X-USER-ID")
        val products = productService.listWithDiscount(userId)
        return ServerResponse
            .status(HttpStatus.OK)
            .bodyValueAndAwait(products.map { it.toResponse() })
            .also {
                logger.info("Reply ${it.rawStatusCode()} for list products with ${products.size} products")
            }
    }
}
