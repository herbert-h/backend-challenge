package com.herbert.samplebff.webservice.base

import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.output.Slf4jLogConsumer

class PostgresContainer {

    companion object {
        private const val CONTAINER_NAME = "postgres-test"
        private const val DB_USERNAME = "postgres"
        private const val DB_PASSWORD = "postgres"
        private const val EXPOSED_PORT = 5432

        private val log: Logger = LoggerFactory.getLogger(PostgresContainer::class.java)
        private val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>("postgres:12.7-alpine")

        fun start(
            dataBaseName: String,
            dbUserName: String = DB_USERNAME,
            dbPassword: String = DB_PASSWORD,
            exposedPort: Int = EXPOSED_PORT,
            containerNameSuffix: String = UUID.randomUUID().toString().replace("-", "").substring(0, 8),
            followOutput: Boolean = false
        ) {
            log.info("Start postgreSQL container")
            applyPostgreContainerConfig(exposedPort, dataBaseName, dbUserName, dbPassword)
            postgresContainer
                .withCreateContainerCmdModifier { it.withName("$CONTAINER_NAME-$containerNameSuffix") }
                .start()
            if (followOutput) postgresContainer.followOutput(Slf4jLogConsumer(log))
        }

        fun execInContainer(vararg commands: String) {
            postgresContainer.execInContainer(*commands)
        }

        private fun applyPostgreContainerConfig(
            exposedPort: Int,
            dataBaseName: String,
            dbUserName: String,
            dbPassword: String
        ) {
            postgresContainer.apply { withExposedPorts(exposedPort) }
                .apply { withCreateContainerCmdModifier { it.withName(CONTAINER_NAME) } }
                .apply { withDatabaseName(dataBaseName) }
                .apply { withUsername(dbUserName) }
                .apply { withPassword(dbPassword) }
        }
    }

    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurationContext: ConfigurableApplicationContext) {
            log.info(
                "Overriding Spring Properties for postgres DB_USERNAME={}, DB_URL={}",
                postgresContainer.username,
                postgresContainer.jdbcUrl
            )
            val r2dbcUrl = postgresContainer.jdbcUrl.replace("jdbc", "r2dbc:pool")
            val values = TestPropertyValues.of(
                "DB_URL=$r2dbcUrl",
                "FLYWAY_URL=${postgresContainer.jdbcUrl}",
                "DB_USERNAME=${postgresContainer.username}",
                "DB_PASSWORD=${postgresContainer.password}"
            )
            values.applyTo(configurationContext)
        }
    }
}
