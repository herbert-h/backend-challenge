package com.herbert.samplebff.thirdparty.helloworld.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "hello-world.grpc")
data class HelloWorldGrpcConfiguration(
    val host: String,
    val port: Int,
    val deadlineInMilliseconds: Long
)
