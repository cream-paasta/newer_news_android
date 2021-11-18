package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetSavedGuUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setSavedGu(value: String) = sharedPrefRepository.setSavedGu(value)
}