package com.herbert.samplebff.thirdparty.helloworld.config

import io.grpc.ManagedChannelBuilder
import io.grpc.examples.helloworld.GreeterGrpcKt
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloWorldGrpcClientConfig(
    private val configuration: HelloWorldGrpcConfiguration
) {
    @Bean
    fun contextGrpcClient() = HelloWorldGrpcClient(
        stub = contextGrpcStub(),
        deadlineInMilliseconds = configuration.deadlineInMilliseconds
    )

    fun contextGrpcStub(): GreeterGrpcKt.GreeterCoroutineStub {
        val channel = ManagedChannelBuilder
            .forAddress(configuration.host, configuration.port)
            .usePlaintext()
            .build()

        return GreeterGrpcKt.GreeterCoroutineStub(channel)
    }
}
