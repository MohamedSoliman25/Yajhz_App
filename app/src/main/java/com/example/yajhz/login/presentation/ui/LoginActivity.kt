package com.example.yajhz.login.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.yajhz.R
import com.example.yajhz.databinding.ActivityLoginBinding
import com.example.yajhz.databinding.ActivitySignupBinding
import com.example.yajhz.home.presentation.ui.HomeActivity
import com.example.yajhz.login.domain.model.LoginRequest
import com.example.yajhz.login.presentation.viewmodel.LoginViewModel
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.signup.presentation.ui.SignupActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.math.log
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleLoginAction()
        observeLogin()
        goToSignUpActivity()
    }

    private fun handleLoginAction(){
        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }
        }
    }
    private fun login(){
        binding.apply {
            viewModel.login("en", LoginRequest(etEmail.text.toString(),
            etPassword.text.toString(),""
            ))
        }
    }
    private fun observeLogin(){
        viewModel.loginResult.observe(this@LoginActivity){resource->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    // Show error message
                    Toast.makeText(this@LoginActivity, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goToSignUpActivity(){
        binding.apply {
            signup.setOnClickListener {
                startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
            }
        }
    }
}