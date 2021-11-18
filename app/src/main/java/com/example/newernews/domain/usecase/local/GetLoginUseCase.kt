package com.example.newernews.domain.usecase.local

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetLoginUseCase@Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getLogin(): Boolean = sharedPrefRepository.isLogin()
}