package org.example.practicekotlin.domain.user.service

import org.example.practicekotlin.domain.user.presentation.dto.request.LoginRequest
import org.example.practicekotlin.domain.user.presentation.dto.request.RefreshRequest
import org.example.practicekotlin.domain.user.presentation.dto.request.RegisterUserRequest
import org.example.practicekotlin.global.auth.jwt.JwtInfo
import org.example.practicekotlin.global.common.BaseResponse

interface UserService {
    fun registerUser(registerUserRequest: RegisterUserRequest): BaseResponse<Unit>
    fun loginUser(loginRequest: LoginRequest): BaseResponse<JwtInfo>
    fun refreshToken(refreshRequest: RefreshRequest): BaseResponse<String>
}