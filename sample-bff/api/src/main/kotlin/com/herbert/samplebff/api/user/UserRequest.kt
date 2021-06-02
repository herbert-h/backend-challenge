package com.herbert.samplebff.api.user

import java.util.Date

data class UserRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date
)
