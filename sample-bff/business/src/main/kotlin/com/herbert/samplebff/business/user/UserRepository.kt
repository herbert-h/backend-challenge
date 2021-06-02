package com.herbert.samplebff.business.user

import com.herbert.samplebff.business.product.model.Product
import com.herbert.samplebff.business.user.model.User
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CoroutineCrudRepository<User, UUID>