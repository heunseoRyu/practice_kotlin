package org.example.practicekotlin.domain.user.domain.entity

import org.example.practicekotlin.domain.user.domain.enums.UserRoles
import jakarta.persistence.*

@Entity
class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // ID (PK)

    @Column(nullable = false)
    val email: String, // Email

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val password: String, // Password

    @Column(nullable = false)
    val role: UserRoles = UserRoles.ROLE_USER

)