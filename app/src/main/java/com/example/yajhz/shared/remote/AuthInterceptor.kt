package com.example.yajhz.shared.remote

import com.example.yajhz.shared.local.PrefsHelper
import com.example.yajhz.shared.util.Utils
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefsHelper:PrefsHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefsHelper.getString(Utils.APP_TOKEN,"")
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}

