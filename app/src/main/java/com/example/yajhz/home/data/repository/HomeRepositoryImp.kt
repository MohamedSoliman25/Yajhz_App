package com.example.yajhz.home.data.repository

import com.example.yajhz.home.domain.model.BaseCategoryResponse
import com.example.yajhz.home.domain.model.PopularResponse
import com.example.yajhz.home.domain.model.TrendingResponse
import com.example.yajhz.home.domain.repository.HomeRepository
import com.example.yajhz.shared.remote.ApiService
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.shared.util.Utils

class HomeRepositoryImp (private val apiService: ApiService):HomeRepository {
    override suspend fun getBaseCategory(language: String): Resource<BaseCategoryResponse> {
        return try {
            val response = apiService.getBaseCategories(language)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }

    override suspend fun getPopular(
        language: String,
        lat: Double,
        lng: Double,
        filter: Int
    ): Resource<PopularResponse> {
        return try {
            val response = apiService.getPopular(language,lat,lng,filter)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }    }

    override suspend fun getTrending(
        language: String,
        lat: Double,
        lng: Double,
        filter: Int
    ): Resource<TrendingResponse> {
        return try {
            val response = apiService.getTrending(language,lat,lng,filter)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }
}