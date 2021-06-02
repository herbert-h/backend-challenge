package com.herbert.samplebff.webservice.resources.product

import com.herbert.samplebff.api.product.ProductRequest
import com.herbert.samplebff.api.product.ProductResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class ProductRouter {

    companion object {
        const val PATH = "/api/products"
        const val TAG = "Product"
    }

    @RouterOperations(
        RouterOperation(
            path = PATH,
            method = [RequestMethod.POST],
            operation = Operation(
                operationId = "post-product",
                tags = [TAG],
                requestBody = RequestBody(
                    content = [Content(schema = Schema(implementation = ProductRequest::class))]
                ),
                responses = [
                    ApiResponse(
                        responseCode = "201",
                        content = [Content(schema = Schema(implementation = ProductResponse::class))]
                    )
                ]
            )
        ),
        RouterOperation(
            path = PATH,
            method = [RequestMethod.GET],
            operation = Operation(
                operationId = "get-product",
                tags = [TAG],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        content = [Content(schema = Schema(implementation = ProductResponse::class))]
                    )
                ]
            )
        ),
        RouterOperation(
            path = PATH,
            method = [RequestMethod.GET],
            operation = Operation(
                operationId = "get-all-products",
                tags = [TAG],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        content = [Content(array = ArraySchema(schema = Schema(implementation = ProductResponse::class)))]
                    )
                ]
            )
        )
    )
    @Bean
    fun productRoute(handler: ProductHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            PATH.nest {
                POST("", handler::createProduct)
                GET("/{product-id}", handler::getProduct)
                GET("", handler::listProducts)
            }
        }
    }
}
