package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetLoginUseCase@Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getLogin(): Boolean = sharedPrefRepository.isLogin()
}