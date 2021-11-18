package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetEmailUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setEmail(value: String) = sharedPrefRepository.setEmail(value)
}