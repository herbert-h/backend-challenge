package com.herbert.samplebff.webservice.resources.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class UserRouter {

    companion object {
        const val PATH = "/api/users"
    }

    @Bean
    fun userRoute(handler: UserHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            PATH.nest {
                POST("", handler::createUser)
                GET("", handler::listUsers)
            }
        }
    }
}
