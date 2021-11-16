package com.example.newernews.presentation.ui.signup

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newernews.R
import com.example.newernews.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUpSubmit.setOnClickListener { _ ->
            // TODO E-Mail 형식 확인, E-Mail 중복 확인, 닉네임 중복 확인(?) 필요
            signUpViewModel.submitSignUp(binding.etSignUpEmail.text.toString(), binding.etSignUpPw.text.toString(), binding.etSignUpName.text.toString())
            binding.signupProgressBar.visibility = View.VISIBLE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        signUpViewModel.isSubmitSuccessLiveData.observe(this, {
            binding.signupProgressBar.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            if (it) {
                Toast.makeText(this, R.string.sign_up_success, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Snackbar.make(binding.root, R.string.sign_up_fali, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}