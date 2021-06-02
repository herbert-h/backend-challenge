package com.herbert.samplebff.webservice.base

import com.herbert.samplebff.Application
import com.herbert.samplebff.business.product.ProductRepository
import com.herbert.samplebff.business.product.model.Product
import java.time.Duration
import javax.annotation.PostConstruct
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.flywaydb.test.FlywayTestExecutionListener
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@ContextConfiguration(
    initializers = [
        PostgresContainer.Initializer::class
    ]
)
@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestExecutionListeners(value = [DependencyInjectionTestExecutionListener::class, FlywayTestExecutionListener::class])
@AutoConfigureWebTestClient
class BaseTestConfig {

    @Autowired
    protected lateinit var webTestClient: WebTestClient

    @Autowired
    protected lateinit var productRepository: ProductRepository

    init {
        PostgresContainer.start(dataBaseName = "sample-bff-test")
        PostgresContainer.execInContainer("psql", "-U", "postgres", "-d",
            "sample-bff-test", "-c", "CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\"")
    }

    @PostConstruct
    fun setUp() {
        val connectTimeout = 30_000L
        webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(connectTimeout)).build()
    }

    protected fun createProduct(
        title: String = "Pen",
        description: String = "Pen color blue",
        priceInCents: Int = 200
    ): Product = runBlocking {
        productRepository.save(
            Product(
                title = title,
                description = description,
                priceInCents = priceInCents
            )
        )
    }

}