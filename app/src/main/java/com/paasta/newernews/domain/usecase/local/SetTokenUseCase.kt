package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setToken(value: String) = sharedPrefRepository.setToken(value)
}