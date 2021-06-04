package com.herbert.samplebff.webservice.resources.user.extension

import com.herbert.samplebff.api.user.UserRequest
import com.herbert.samplebff.api.user.UserResponse
import com.herbert.samplebff.business.user.model.User

fun UserRequest.toEntity() = User(
    firstName = this.firstName,
    lastName = this.lastName,
    dateOfBirth = this.dateOfBirth
)

fun User.toResponse() = UserResponse(
    id = id?.toString() ?: throw Exception(), //TODO - create exception
    firstName = this.firstName,
    lastName = this.lastName,
    dateOfBirth = this.dateOfBirth
)
