package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetSavedCityUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setSavedCity(value: String) = sharedPrefRepository.setSavedCity(value)
}