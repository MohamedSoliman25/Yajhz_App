package com.example.yajhz.signup.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.yajhz.R
import com.example.yajhz.databinding.ActivitySignupBinding
import com.example.yajhz.home.presentation.ui.HomeActivity
import com.example.yajhz.login.presentation.ui.LoginActivity
import com.example.yajhz.shared.local.PrefsHelper
import com.example.yajhz.shared.util.Resource
import com.example.yajhz.shared.util.Utils
import com.example.yajhz.signup.domain.model.SignUpRequest
import com.example.yajhz.signup.presentation.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()
    @Inject
    lateinit var prefsHelper:PrefsHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToHomeActivity()
        handleSignUpAction()
        observeSignUP()
        goToLoginActivity()
    }

    private fun goToHomeActivity(){
        if (prefsHelper.getString(Utils.APP_TOKEN,"")!!.isNotEmpty()){
            finish()
            startActivity(Intent(this@SignupActivity,HomeActivity::class.java))
        }
    }

    private fun handleSignUpAction(){
        binding.apply {
            btnSignup.setOnClickListener {
                signUp()
            }
        }
    }

    private fun signUp(){
        binding.apply {
            signUpViewModel.signUp("en", SignUpRequest(et_name.text.toString(),
            etEmail.text.toString(),
                etPassword.text.toString(),
            etConfirmPassword.text.toString(),
            etPhone.text.toString(),""))
        }
    }
    private fun observeSignUP(){
        signUpViewModel.signUpResult.observe(this@SignupActivity){resource->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // Handle successful signup
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@SignupActivity, "Signup successful", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    // Show error message
                    Toast.makeText(this@SignupActivity, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun goToLoginActivity(){
        binding.apply {
            login.setOnClickListener {
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            }
        }
    }
}