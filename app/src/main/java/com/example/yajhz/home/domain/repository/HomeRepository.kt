package com.example.yajhz.home.domain.repository

import com.example.yajhz.home.domain.model.BaseCategoryResponse
import com.example.yajhz.home.domain.model.PopularResponse
import com.example.yajhz.home.domain.model.TrendingResponse
import com.example.yajhz.shared.util.Resource
import retrofit2.Response

interface HomeRepository {
    suspend fun getBaseCategory(language: String):Resource<BaseCategoryResponse>
    suspend fun getPopular(language: String,lat:Double,lng:Double,filter:Int):Resource<PopularResponse>
    suspend fun getTrending(language: String,lat:Double,lng:Double,filter:Int):Resource<TrendingResponse>


}