import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectVersion: String by ext
val springBootStarterVersion: String by ext
val springCloudVersion: String by ext
val kotlinxCoroutinesVersion: String by ext
val kotlinVersion: String by ext
val openApiVersion: String by ext
val jacksonVersion: String by ext
val kotlinTestVersion: String by ext
val mockkVersion: String by ext
val embeddedDatabaseSpringTestVersion: String by ext
val embeddedPostgresVersion: String by ext
val postgresqlVersion: String by ext
val flywayVersion: String by ext
val grpcKotlinStub: String by ext
val grpcSpringBootStarter: String by ext

plugins {
    id("org.springframework.boot") version "2.4.6" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.google.protobuf") version "0.8.15"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"

    idea
    java
}

group = "com.herbert.samplebff"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

allprojects {
    version = projectVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("idea")
    }

    group = "com.herbert.samplebff"

    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
            exclude(group = "ch.qos.logback", module = "logback-classic")
            exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootStarterVersion")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
        dependencies {
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutinesVersion")
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:$kotlinxCoroutinesVersion")
            dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
            dependency("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

            dependency("org.springdoc:springdoc-openapi-kotlin:$openApiVersion")
            dependency("org.springdoc:springdoc-openapi-webflux-ui:$openApiVersion")

            dependency("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
            dependency("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
            dependency("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

            dependency("io.kotlintest:kotlintest-runner-junit5:$kotlinTestVersion")
            dependency("io.kotlintest:kotlintest-extensions-spring:$kotlinTestVersion")
            dependency("io.mockk:mockk:$mockkVersion")
            dependency("io.zonky.test:embedded-database-spring-test:$embeddedDatabaseSpringTestVersion")
            dependency("io.zonky.test:embedded-postgres:$embeddedPostgresVersion")
            dependency("org.postgresql:postgresql:$postgresqlVersion")
            dependency("org.flywaydb:flyway-core:$flywayVersion")
            dependency("io.grpc:grpc-kotlin-stub:$grpcKotlinStub")
            dependency("io.github.lognet:grpc-spring-boot-starter:$grpcSpringBootStarter")
        }
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // Spring
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")


        // Jackson
        implementation("com.fasterxml.jackson.core:jackson-core")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

        // Testing
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
        testImplementation("io.kotlintest:kotlintest-runner-junit5")
        testImplementation("io.kotlintest:kotlintest-extensions-spring")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug")
        testImplementation("io.mockk:mockk")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    tasks {
        test {
            useJUnitPlatform()
        }

        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }
    }
}
