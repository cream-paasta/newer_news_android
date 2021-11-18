package com.paasta.newernews.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.paasta.newernews.MainActivity
import com.paasta.newernews.R
import com.paasta.newernews.databinding.ActivityLoginBinding
import com.paasta.newernews.presentation.ui.signup.SignUpActivity
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
            //loginViewModel.setLoginSuccess()
            loginViewModel.requestLogin(binding.loginId.text.toString(), binding.loginPw.text.toString())
            binding.loginProgressBar.visibility = View.VISIBLE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        binding.btnSignUp.setOnClickListener { _ ->
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        loginViewModel.isLoginSuccessLiveData.observe(this, {
            binding.loginProgressBar.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(binding.root, R.string.login_fail, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}