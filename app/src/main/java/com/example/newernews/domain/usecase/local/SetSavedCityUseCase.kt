package com.example.newernews.domain.usecase.local

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetSavedCityUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setSavedCity(value: String) = sharedPrefRepository.setSavedCity(value)
}