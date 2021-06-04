package com.herbert.samplebff.api.user

import java.time.LocalDate

data class UserRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
)
