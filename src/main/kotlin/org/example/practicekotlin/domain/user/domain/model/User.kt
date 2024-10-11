package org.example.practicekotlin.domain.user.domain.model

import org.example.practicekotlin.domain.user.domain.enums.UserRoles

data class User(
    val id: Long? = null,
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val role: UserRoles = UserRoles.ROLE_USER
)
