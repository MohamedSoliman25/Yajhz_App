package com.example.yajhz.signup.data.repository

import android.util.Log
import com.example.yajhz.shared.remote.ApiService
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.signup.domain.model.SignUpRequest
import com.example.yajhz.signup.domain.model.SignUpResponse
import com.example.yajhz.signup.domain.repository.SignUpRepository
import java.util.regex.Pattern

class SignUpRepositoryImp(private val apiService: ApiService) :SignUpRepository {
override suspend fun signUp(language:String,signUpRequest: SignUpRequest): Resource<SignUpResponse> {
    val validationResult = validateSignUpRequest(signUpRequest)
    if (validationResult != null) {
        return validationResult
    }

    return try {
        val response = apiService.signUp(language,signUpRequest)
        if (response.isSuccessful && response.body() != null) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(response.message() ?: "Unknown error")
        }
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Network error")
    }
}

    private fun validateSignUpRequest(signUpRequest: SignUpRequest): Resource<SignUpResponse>? {
        if (signUpRequest.name.isEmpty() ||
            signUpRequest.email.isEmpty() ||
            signUpRequest.password.isEmpty() ||
            signUpRequest.confirm_password.isEmpty() ||
            signUpRequest.phone.isEmpty()
        ) {
            return Resource.Error("All fields are required")
        }

        if (signUpRequest.name.length > 14) {
            return Resource.Error("Name must be  at least 14 characters long")
        }
        if (!isValidEmail(signUpRequest.email)) {
            return Resource.Error("Invalid email format")
        }

        if (signUpRequest.phone.length !=11) {
            return Resource.Error("Phone must be 11 numbers")
        }
        if (signUpRequest.password.length < 8) {
            return Resource.Error("Password must be at least 8 characters long")
        }

        if (signUpRequest.password != signUpRequest.confirm_password) {
            return Resource.Error("Passwords do not match")
        }

        return null
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
        return emailPattern.matcher(email).matches()
    }
}