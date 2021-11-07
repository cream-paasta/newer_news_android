package com.example.newernews.presentation.ui.login

import androidx.lifecycle.ViewModel
import com.example.newernews.domain.usecase.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setLoginUseCase: SetLoginUseCase
) : ViewModel() {

    fun setLoginSuccess() = setLoginUseCase.setLogin(value = true)
}