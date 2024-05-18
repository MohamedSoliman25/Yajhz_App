package com.example.yajhz.shared.di

import com.example.yajhz.home.data.repository.HomeRepositoryImp
import com.example.yajhz.home.domain.repository.HomeRepository
import com.example.yajhz.login.data.repository.LoginRepositoryImp
import com.example.yajhz.login.domain.repository.LoginRepository
import com.example.yajhz.shared.local.PrefsHelper
import com.example.yajhz.shared.remote.ApiService
import com.example.yajhz.shared.remote.AuthInterceptor
import com.example.yajhz.shared.util.Utils
import com.example.yajhz.signup.data.repository.SignUpRepositoryImp
import com.example.yajhz.signup.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object YajhzModule  {

    @Provides
    fun provideBaseUrl(): String = Utils.BASE_URL

    @Singleton
    @Provides
    fun provideAuthInterceptor(prefsHelper: PrefsHelper): AuthInterceptor {
        return AuthInterceptor(prefsHelper)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String,okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideSignUpRepository(apiService: ApiService): SignUpRepository = SignUpRepositoryImp(apiService)
    @Provides
    fun provideLoginRepository(apiService: ApiService,prefsHelper: PrefsHelper): LoginRepository = LoginRepositoryImp(apiService,prefsHelper)

    @Provides
    fun provideHomeRepository(apiService: ApiService): HomeRepository = HomeRepositoryImp(apiService)

    @Provides
    @Singleton
    fun providePrefsHelper(): PrefsHelper = PrefsHelper


}