package com.example.newernews.domain.usecase.local

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetSavedDongUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setSavedDong(value: String) = sharedPrefRepository.setSavedDong(value)
}