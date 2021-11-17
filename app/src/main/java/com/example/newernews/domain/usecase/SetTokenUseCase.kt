package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setToken(value: String) = sharedPrefRepository.setToken(value)
}