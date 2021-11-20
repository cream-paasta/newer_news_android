package com.paasta.newernews.presentation.ui.mainfragment.mypage

import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.usecase.local.GetEmailUseCase
import com.paasta.newernews.domain.usecase.local.GetUserNameUseCase
import com.paasta.newernews.domain.usecase.local.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val setLoginUseCase: SetLoginUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getEmailUseCase: GetEmailUseCase
): ViewModel() {

    fun setLoginFalse() {
        setLoginUseCase.setLogin(false)
    }

    fun getUserName(): String {
        return getUserNameUseCase.getUserName() ?: ""
    }

    fun getEmail(): String {
        return getEmailUseCase.getEmail() ?: ""
    }
}