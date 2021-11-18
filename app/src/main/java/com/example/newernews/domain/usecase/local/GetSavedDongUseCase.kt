package com.example.newernews.domain.usecase.local

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetSavedDongUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getSavedDong(): String? = sharedPrefRepository.getSavedDong()
}