package com.example.newernews.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newernews.MainActivity
import com.example.newernews.R
import com.example.newernews.databinding.ActivityLoginBinding
import com.example.newernews.presentation.ui.signup.SignUpActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener { _ ->
            // 테스트를 위해 강제 로그인 시킴
            loginViewModel.setLoginSuccess()
            //loginViewModel.requestLogin(binding.loginId.text.toString(), binding.loginPw.text.toString())
        }

        binding.btnSignUp.setOnClickListener { _ ->
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        loginViewModel.isLoginSuccessLiveData.observe(this, {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Snackbar.make(binding.root, R.string.login_fail, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}