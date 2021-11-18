package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetSavedGuUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getSavedGu(): String? = sharedPrefRepository.getSavedGu()
}