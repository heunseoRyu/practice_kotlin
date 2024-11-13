package org.example.practicekotlin.global.auth.jwt

import org.example.practicekotlin.domain.user.domain.UserRepository
import org.example.practicekotlin.domain.user.domain.mapper.UserMapper
import org.example.practicekotlin.domain.user.exception.UserErrorCode
import org.example.practicekotlin.global.exception.CustomException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JwtUserDetailsService (
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): UserDetails {
        return JwtUserDetails (
            user = userMapper.toDomain(
               entity = userRepository.findByEmail(email)?: throw CustomException(UserErrorCode.USER_NOT_FOUND)
            )
        )
    }

}