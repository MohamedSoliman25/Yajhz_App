package com.example.yajhz.signup.domain.repository

import com.example.yajhz.shared.util.Resource
import com.example.yajhz.signup.domain.model.SignUpRequest
import com.example.yajhz.signup.domain.model.SignUpResponse
import retrofit2.Response

interface SignUpRepository {
    suspend fun signUp(language:String,signUpRequest: SignUpRequest): Resource<SignUpResponse>
}