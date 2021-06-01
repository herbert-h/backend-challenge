package com.herbert.samplebff.business.helloworld

import org.springframework.stereotype.Service

@Service
class HelloWorldService(
    private val helloWorldGateway: HelloWorldGateway
) {
    suspend fun hello(name: String): String {
        return helloWorldGateway.hello(name)
    }
}
