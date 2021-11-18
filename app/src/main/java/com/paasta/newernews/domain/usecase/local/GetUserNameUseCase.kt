package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getUserName(): String? = sharedPrefRepository.getUserName()
}