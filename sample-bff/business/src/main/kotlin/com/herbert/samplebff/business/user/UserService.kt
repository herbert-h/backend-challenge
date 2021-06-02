package com.herbert.samplebff.business.user

import com.herbert.samplebff.business.user.model.User
import kotlinx.coroutines.flow.toList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    suspend fun create(user: User): User {
        return userRepository.save(user)
            .also { logger.debug("New user created [userId=${user.id}]") }
    }

    suspend fun list(): List<User> {
        logger.debug("List users")
        return userRepository.findAll().toList()
    }
}
