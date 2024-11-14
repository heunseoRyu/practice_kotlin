package org.example.practicekotlin.domain.user.service

import org.example.practicekotlin.domain.user.domain.UserRepository
import org.example.practicekotlin.domain.user.domain.mapper.UserMapper
import org.example.practicekotlin.domain.user.exception.UserErrorCode
import org.example.practicekotlin.domain.user.presentation.dto.request.LoginRequest
import org.example.practicekotlin.domain.user.presentation.dto.request.RefreshRequest
import org.example.practicekotlin.domain.user.presentation.dto.request.RegisterUserRequest
import org.example.practicekotlin.global.auth.jwt.JwtInfo
import org.example.practicekotlin.global.auth.jwt.JwtUtils
import org.example.practicekotlin.global.auth.jwt.exception.JwtErrorCode
import org.example.practicekotlin.global.auth.jwt.exception.type.JwtErrorType
import org.example.practicekotlin.global.common.BaseResponse
import org.example.practicekotlin.global.exception.CustomException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val bytePasswordEncoder: BCryptPasswordEncoder,
    private val jwtUtils: JwtUtils
) : UserService {

    @Transactional
    override fun registerUser(registerUserRequest: RegisterUserRequest): BaseResponse<Unit> {

        if(userRepository.existsByEmail(registerUserRequest.email)) throw CustomException(UserErrorCode.USER_ALREADY_EXIST)

        userRepository.save(
            userMapper.toEntity(
                userMapper.toDomain(registerUserRequest, bytePasswordEncoder.encode(registerUserRequest.password.trim()))
            )
        )

        return BaseResponse(
            message = "회원가입 성공"
        )

    }

    @Transactional(readOnly = true)
    override fun loginUser(loginRequest: LoginRequest): BaseResponse<JwtInfo> {

        val user = userRepository.findByEmail(loginRequest.email)?: throw CustomException(UserErrorCode.USER_NOT_FOUND)

        if (!bytePasswordEncoder.matches(loginRequest.password,user.password)) throw CustomException(UserErrorCode.USER_NOT_MATCH)

        return BaseResponse(
            message = "로그인 성공",
            data = jwtUtils.generate(
                user = userMapper.toDomain(user)
            )
        )

    }

    @Transactional(readOnly = true)
    override fun refreshToken(refreshRequest: RefreshRequest): BaseResponse<String> {
        val token = jwtUtils.getToken(refreshRequest.refreshToken)

        if (jwtUtils.checkTokenInfo(token) == JwtErrorType.ExpiredJwtException) {
            throw CustomException(JwtErrorCode.JWT_TOKEN_EXPIRED)
        }

        val user = userRepository.findByEmail(
            jwtUtils.getUsername(token)
        )

        return BaseResponse (
            message = "리프레시 성공 !",
            data = jwtUtils.refreshToken(
                user = userMapper.toDomain(user!!)
            )
        )
    }
}