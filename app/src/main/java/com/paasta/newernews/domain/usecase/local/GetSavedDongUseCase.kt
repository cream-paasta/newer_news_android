package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetSavedDongUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getSavedDong(): String? = sharedPrefRepository.getSavedDong()
}