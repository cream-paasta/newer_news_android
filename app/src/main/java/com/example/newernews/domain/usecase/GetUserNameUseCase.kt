package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.SharedPrefRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {
    fun getUserName(): String? = sharedPrefRepository.getUserName()
}