package com.herbert.samplebff.webservice.resources.helloworld

import com.herbert.samplebff.api.helloworld.HelloWorldRequest
import com.herbert.samplebff.api.helloworld.HelloWorldResponse
import com.herbert.samplebff.business.helloworld.HelloWorldService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class HelloWorldHandler(
    val helloWorldService: HelloWorldService
) {
    suspend fun hello(request: ServerRequest): ServerResponse {
        val body: HelloWorldRequest = request.awaitBody()
        val message = helloWorldService.hello(body.name)
        return ServerResponse
            .status(HttpStatus.OK)
            .bodyValueAndAwait(HelloWorldResponse(message))
    }
}
