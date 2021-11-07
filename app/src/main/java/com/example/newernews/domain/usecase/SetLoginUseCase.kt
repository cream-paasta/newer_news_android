package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetLoginUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setLogin(value: Boolean) = sharedPrefRepository.setLogin(value)
}