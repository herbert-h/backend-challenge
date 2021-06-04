package com.herbert.samplebff.business.product

import com.herbert.samplebff.business.exception.NotFoundException
import com.herbert.samplebff.business.product.model.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductServiceTest {
    private val discountGateway = mockkClass(DiscountGateway::class)
    private val productRepository = mockkClass(ProductRepository::class)
    private val service: ProductService by lazy {
        ProductService(
            productRepository = productRepository,
            discountGateway = discountGateway
        )
    }

    @Test
    fun `it should get product by id`() {
        val product = dummyProduct()

        coEvery { productRepository.findById(eq(product.id!!)) } returns product

        val respProduct = runBlocking {
            service.findById(product.id!!)
        }

        coVerify(exactly = 1) { productRepository.findById(eq(product.id!!)) }

        assert(product == respProduct.copy(id = null))
    }

    @Test
    fun `it should throw exception when not found product by id`() {
        val id = UUID.randomUUID()

        coEvery { productRepository.findById(eq(id)) } returns null

        assertThrows<NotFoundException> {
            runBlocking {
                service.findById(id)
            }
        }
    }

    private fun dummyProduct() = Product(
        id = UUID.randomUUID(),
        title = "title",
        description = "description",
        priceInCents = 5000
    )
}