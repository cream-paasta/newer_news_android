package com.paasta.newernews.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.paasta.newernews.MainActivity
import com.paasta.newernews.databinding.ActivityOnboardingBinding
import com.paasta.newernews.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity: AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (onboardingViewModel.getLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val btnStart = binding.btnOnboardingStart
        btnStart.setOnClickListener{ _ ->
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}