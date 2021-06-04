package com.herbert.samplebff.business.user.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@Table("users")
data class User(
    @Id
    val id: UUID? = null,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val createdAt: Instant = Instant.now(),
)
