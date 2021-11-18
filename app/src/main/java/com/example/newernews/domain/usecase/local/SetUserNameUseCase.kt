package com.example.newernews.domain.usecase.local

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class SetUserNameUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun setUserName(value: String) = sharedPrefRepository.setUserName(value)
}