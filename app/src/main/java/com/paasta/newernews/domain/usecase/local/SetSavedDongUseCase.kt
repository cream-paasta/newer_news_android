package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetSavedDongUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setSavedDong(value: String) = sharedPrefRepository.setSavedDong(value)
}