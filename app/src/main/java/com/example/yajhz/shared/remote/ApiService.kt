package com.example.yajhz.shared.remote

import com.example.yajhz.home.domain.model.BaseCategoryResponse
import com.example.yajhz.home.domain.model.PopularResponse
import com.example.yajhz.home.domain.model.TrendingResponse
import com.example.yajhz.login.domain.model.LoginRequest
import com.example.yajhz.login.domain.model.LoginResponse
import com.example.yajhz.signup.domain.model.SignUpRequest
import com.example.yajhz.signup.domain.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("client-register")
    suspend fun signUp(
        @Header("lang") language: String,
       @Body signUpRequest: SignUpRequest
    ): Response<SignUpResponse>

    @POST("login")
    suspend fun login(
        @Header("lang") language: String,
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("home-base-categories")
    suspend fun getBaseCategories(
        @Header("lang") language: String
    ):Response<BaseCategoryResponse>

    @GET("popular-sellers")
    suspend fun getPopular(
        @Header("lang") language: String,
        @Query("lat") lat:Double,
        @Query("lng") lng:Double,
        @Query("filter") filter:Int
    ):Response<PopularResponse>

    @GET("trending-sellers")
    suspend fun getTrending(
        @Header("lang") language: String,
        @Query("lat") lat:Double,
        @Query("lng") lng:Double,
        @Query("filter") filter:Int
    ):Response<TrendingResponse>
}