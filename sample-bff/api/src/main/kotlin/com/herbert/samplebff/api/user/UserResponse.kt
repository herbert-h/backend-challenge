package com.herbert.samplebff.api.user

import com.herbert.samplebff.api.product.ProductResponse
import java.util.Date

data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date,
)

