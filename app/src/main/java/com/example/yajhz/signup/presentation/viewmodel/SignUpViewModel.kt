package com.example.yajhz.signup.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.signup.domain.model.SignUpRequest
import com.example.yajhz.signup.domain.model.SignUpResponse
import com.example.yajhz.signup.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpRepository: SignUpRepository) : ViewModel() {

    private val _signUpResult = MutableLiveData<Resource<SignUpResponse>>()
    val signUpResult: LiveData<Resource<SignUpResponse>> get() = _signUpResult

    fun signUp(language:String,signUpRequest: SignUpRequest) {
        _signUpResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = signUpRepository.signUp(language,signUpRequest)
            _signUpResult.value = result
        }
    }
}