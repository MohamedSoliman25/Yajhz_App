package com.example.yajhz.login.domain.repository

import com.example.yajhz.login.domain.model.LoginRequest
import com.example.yajhz.login.domain.model.LoginResponse
import com.example.yajhz.shared.util.Resource

interface LoginRepository {
    suspend fun login(language:String,loginRequest: LoginRequest): Resource<LoginResponse>

}