package com.herbert.samplebff.webservice.resources.helloworld

import com.herbert.samplebff.api.helloworld.HelloWorldResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class HelloWorldRouter {

    companion object {
        const val PATH = "/api/helloworld"
    }

    @Bean
    fun helloWorldRoute(handler: HelloWorldHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            PATH.nest {
                POST("", handler::hello)
            }
        }
    }
}
