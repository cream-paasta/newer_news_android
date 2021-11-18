package com.example.newernews.domain.usecase.local

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetSavedGuUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getSavedGu(): String? = sharedPrefRepository.getSavedGu()
}