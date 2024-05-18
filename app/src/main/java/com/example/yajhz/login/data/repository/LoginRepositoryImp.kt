package com.example.yajhz.login.data.repository

import com.example.yajhz.login.domain.model.LoginRequest
import com.example.yajhz.login.domain.model.LoginResponse
import com.example.yajhz.login.domain.repository.LoginRepository
import com.example.yajhz.shared.local.PrefsHelper
import com.example.yajhz.shared.remote.ApiService
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.shared.util.Utils
import java.util.regex.Pattern
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val apiService: ApiService,private val prefsHelper:PrefsHelper):LoginRepository {
    override suspend fun login(
        language: String,
        loginRequest: LoginRequest
    ): Resource<LoginResponse> {
        val validationResult = validateLoginRequest(loginRequest)
        if (validationResult != null) {
            return validationResult
        }
        return try {
            val response = apiService.login(language,loginRequest)
            if (response.isSuccessful && response.body() != null) {
                prefsHelper.putString(Utils.APP_TOKEN,response.body()!!.data.token)
                prefsHelper.saveUserData(response.body()!!.data)
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }


    private fun validateLoginRequest(loginRequest: LoginRequest): Resource<LoginResponse>? {
        if (
            loginRequest.email.isEmpty() ||
            loginRequest.password.isEmpty()
        ) {
            return Resource.Error("All fields are required")
        }


        if (!isValidEmail(loginRequest.email)) {
            return Resource.Error("Invalid email format")
        }


        if (loginRequest.password.length < 8) {
            return Resource.Error("Password must be at least 8 characters long")
        }



        return null
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
        return emailPattern.matcher(email).matches()
    }
}