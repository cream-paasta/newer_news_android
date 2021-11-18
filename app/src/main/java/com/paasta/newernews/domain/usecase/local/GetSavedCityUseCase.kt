package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetSavedCityUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getSavedCity(): String? = sharedPrefRepository.getSavedCity()
}