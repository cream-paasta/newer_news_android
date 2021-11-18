package com.paasta.newernews.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.usecase.local.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {

    fun getLogin(): Boolean = getLoginUseCase.getLogin()
}