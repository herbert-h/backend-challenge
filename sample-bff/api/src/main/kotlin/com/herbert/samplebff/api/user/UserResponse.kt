package com.herbert.samplebff.api.user

import java.time.LocalDate

data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
)
