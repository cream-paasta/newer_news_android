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

    fun getEmail(): String? = sharedPrefModule.email
    fun setEmail(value: String) {
        sharedPrefModule.email = value
    }

    fun getToken(): String? = sharedPrefModule.token
    fun setToken(value: String) {
        sharedPrefModule.token = value
    }

    fun getUserName(): String? = sharedPrefModule.userName
    fun setUserName(value: String) {
        sharedPrefModule.userName = value
    }
}