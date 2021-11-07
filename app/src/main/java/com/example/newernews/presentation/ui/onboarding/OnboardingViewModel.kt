package com.example.newernews.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.newernews.domain.usecase.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {

    fun getLogin(): Boolean = getLoginUseCase.getLogin()
}