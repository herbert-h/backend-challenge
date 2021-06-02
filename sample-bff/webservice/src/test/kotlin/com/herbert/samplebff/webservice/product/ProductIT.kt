package com.herbert.samplebff.webservice.product

import com.herbert.samplebff.api.product.ProductRequest
import com.herbert.samplebff.api.product.ProductResponse
import com.herbert.samplebff.webservice.base.BaseTestConfig
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.http.MediaType

class ProductIT: BaseTestConfig() {

    @Test
    @FlywayTest(invokeCleanDB = true)
    fun `Should create a product`() {
        val request = ProductRequest(
            title = "Mouse",
            description = "Mouse Wireless",
            priceInCents = 8000
        )
        val response = webTestClient.post()
            .uri("/api/products")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectBody(ProductResponse::class.java)
            .returnResult()
            .responseBody!!

        Assertions.assertAll(
            Executable { Assertions.assertNotNull(response.id) },
            Executable { assertNotNull(response.id) },
            Executable { assertEquals(request.title, response.title) },
            Executable { assertEquals(request.description, response.description) },
            Executable { assertEquals(request.priceInCents, response.priceInCents) }
        )
    }

    @Test
    @FlywayTest(invokeCleanDB = true)
    fun `Should return a product by id`() {
        val product = createProduct()

        val response = webTestClient.get()
            .uri("/api/products/${product.id}")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody(ProductResponse::class.java)
            .returnResult()
            .responseBody!!

        Assertions.assertAll(
            Executable { assertEquals(product.id.toString(), response.id) },
            Executable { assertEquals(product.title, response.title) },
            Executable { assertEquals(product.description, response.description) },
            Executable { assertEquals(product.priceInCents, response.priceInCents) }
        )
    }

    // I didn't go so far as to mock the rpc calls for complete IT tests
}
