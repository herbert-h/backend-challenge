plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":sample-bff-api"))
    implementation(project(":sample-bff-business"))
    implementation(project(":sample-bff-database"))
    implementation(project(":sample-bff-third-party"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    implementation("org.springdoc:springdoc-openapi-kotlin")
    implementation("org.springdoc:springdoc-openapi-webflux-ui")
}

springBoot {
    buildInfo()
}