package com.paasta.newernews.domain.usecase.local

import com.paasta.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetUserNameUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setUserName(value: String) = sharedPrefRepository.setUserName(value)
}