package com.herbert.samplebff.thirdparty.helloworld

import com.herbert.samplebff.business.helloworld.HelloWorldGateway
import com.herbert.samplebff.thirdparty.helloworld.config.HelloWorldGrpcClient
import io.grpc.examples.helloworld.HelloRequest
import java.util.concurrent.TimeUnit
import org.springframework.stereotype.Component

@Component
class HelloWorldGatewayImpl(
    private val client: HelloWorldGrpcClient
): HelloWorldGateway {

    override suspend fun hello(name: String): String {
        val request = HelloRequest.newBuilder()
            .setName(name)
            .build()

        return client.stub
            .withDeadlineAfter(client.deadlineInMilliseconds, TimeUnit.MILLISECONDS)
            .sayHello(request)
            .message
    }

}