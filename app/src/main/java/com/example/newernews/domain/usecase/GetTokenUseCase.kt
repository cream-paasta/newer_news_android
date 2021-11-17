package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getToken(): String? = sharedPrefRepository.getToken()
}