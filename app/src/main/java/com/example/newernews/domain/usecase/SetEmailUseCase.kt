package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetEmailUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setEmail(value: String) = sharedPrefRepository.setEmail(value)
}