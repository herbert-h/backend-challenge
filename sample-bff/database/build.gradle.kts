plugins {
    id("org.flywaydb.flyway") version "6.4.2"
}

dependencies {
    implementation(project(":sample-bff-business"))
    runtimeOnly("io.r2dbc:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")
}

flyway {
    connectRetries = 1
    baselineOnMigrate = true
    baselineVersion = "3.0"
}
