package com.herbert.samplebff.webservice.resources.user

import com.herbert.samplebff.api.user.UserRequest
import com.herbert.samplebff.business.user.UserService
import com.herbert.samplebff.webservice.resources.user.extension.toEntity
import com.herbert.samplebff.webservice.resources.user.extension.toResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class UserHandler(
    val userService: UserService
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(UserHandler::class.java)
    }

    suspend fun createUser(request: ServerRequest): ServerResponse {
        logger.debug("Receive request to create new user")
        val body: UserRequest = request.awaitBody()
        val user = userService.create(body.toEntity())
        return ServerResponse
            .status(HttpStatus.CREATED)
            .bodyValueAndAwait(user.toResponse())
            .also { logger.info("Reply ${it.rawStatusCode()} for create user with userId=${user.id}") }
    }

    suspend fun listUsers(request: ServerRequest): ServerResponse {
        logger.debug("Receive request to list users")
        val user = userService.list()
        return ServerResponse
            .status(HttpStatus.OK)
            .bodyValueAndAwait(user.map { it.toResponse() })
            .also {
                logger.info("Reply ${it.rawStatusCode()} for list users with ${user.size} users")
            }
    }
}
