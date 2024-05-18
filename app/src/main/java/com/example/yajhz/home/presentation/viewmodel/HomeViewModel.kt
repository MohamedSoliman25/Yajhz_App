package com.example.yajhz.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yajhz.home.domain.model.BaseCategoryResponse
import com.example.yajhz.home.domain.model.PopularResponse
import com.example.yajhz.home.domain.model.TrendingResponse
import com.example.yajhz.home.domain.repository.HomeRepository
import com.example.yajhz.login.domain.model.LoginRequest
import com.example.yajhz.login.domain.model.LoginResponse
import com.example.yajhz.shared.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _baseCategoryResult = MutableLiveData<Resource<BaseCategoryResponse>>()
    val baseCategoryResult: LiveData<Resource<BaseCategoryResponse>> get() = _baseCategoryResult

    private val _popularResult = MutableLiveData<Resource<PopularResponse>>()
    val popularResult: LiveData<Resource<PopularResponse>> get() = _popularResult

    private val _trendingResult = MutableLiveData<Resource<TrendingResponse>>()
    val trendingResult: LiveData<Resource<TrendingResponse>> get() = _trendingResult

    fun getBaseCategory(language:String) {
        _baseCategoryResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = homeRepository.getBaseCategory(language)
            _baseCategoryResult.value = result
        }
    }

    fun getPopular(
        language: String,
        lat: Double,
        lng: Double,
        filter: Int
    ){
        _popularResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = homeRepository.getPopular(language,lat,lng, filter)
            _popularResult.value = result
        }
    }

    fun getTrending(
        language: String,
        lat: Double,
        lng: Double,
        filter: Int
    ){
        _trendingResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = homeRepository.getTrending(language,lat,lng, filter)
            _trendingResult.value = result
        }
    }
}