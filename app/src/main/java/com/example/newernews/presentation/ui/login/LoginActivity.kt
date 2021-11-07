package com.example.newernews.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newernews.databinding.ActivityLoginBinding
import com.example.newernews.presentation.ui.singup.SingUpActivity
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
            // TODO 로그인 기능 구현 필요
            loginViewModel.setLoginSuccess()
        }

        // TODO 회원가입 버튼 기능 구현
        binding.btnSingUp.setOnClickListener { _ ->
            startActivity(Intent(this, SingUpActivity::class.java))
        }
    }
}