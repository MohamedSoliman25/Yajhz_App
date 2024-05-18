package com.example.yajhz.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yajhz.login.domain.model.LoginRequest
import com.example.yajhz.login.domain.model.LoginResponse
import com.example.yajhz.login.domain.repository.LoginRepository
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.signup.domain.model.SignUpRequest
import com.example.yajhz.signup.domain.model.SignUpResponse
import com.example.yajhz.signup.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: LiveData<Resource<LoginResponse>> get() = _loginResult

    fun login(language:String,loginRequest: LoginRequest) {
        _loginResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = loginRepository.login(language,loginRequest)
            _loginResult.value = result
        }
    }
}