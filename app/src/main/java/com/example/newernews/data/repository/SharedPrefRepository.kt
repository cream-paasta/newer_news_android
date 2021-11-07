package com.example.newernews.data.repository

import com.example.newernews.data.local.SharedPrefModule
import javax.inject.Inject

class SharedPrefRepository @Inject constructor(
    private val sharedPrefModule: SharedPrefModule
) {
    fun isLogin(): Boolean = sharedPrefModule.login

    fun setLogin(value: Boolean) {
        sharedPrefModule.login = value
    }
}