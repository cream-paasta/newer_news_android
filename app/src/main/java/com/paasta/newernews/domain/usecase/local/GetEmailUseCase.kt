package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetEmailUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getEmail(): String? = sharedPrefRepository.getEmail()
}