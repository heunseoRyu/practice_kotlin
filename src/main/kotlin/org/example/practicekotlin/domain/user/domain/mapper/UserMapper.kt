package org.example.practicekotlin.domain.user.domain.mapper

import org.example.practicekotlin.domain.user.domain.entity.UserEntity
import org.example.practicekotlin.domain.user.domain.model.User
import org.example.practicekotlin.domain.user.presentation.dto.request.RegisterUserRequest
import org.example.practicekotlin.global.common.Mapper
import org.springframework.stereotype.Component

@Component
class UserMapper(
): Mapper<User, UserEntity> {
    override fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            email = entity.email,
            name = entity.name,
            password = entity.password
        )
    }

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            email = domain.email,
            name = domain.name,
            password = domain.password
        )
    }

    fun toDomain(registerUserRequest: RegisterUserRequest, password: String): User {
        return User(
            email = registerUserRequest.email,
            name = registerUserRequest.name,
            password = password
        )
    }
}