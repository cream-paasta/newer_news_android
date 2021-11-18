package com.paasta.newernews.data.repository

import com.paasta.newernews.data.local.SharedPrefModule
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

    fun getSavedCity(): String? = sharedPrefModule.savedCity
    fun setSavedCity(value: String) {
        sharedPrefModule.savedCity = value
    }

    fun getSavedGu(): String? = sharedPrefModule.savedGu
    fun setSavedGu(value: String) {
        sharedPrefModule.savedGu = value
    }

    fun getSavedDong(): String? = sharedPrefModule.savedDong
    fun setSavedDong(value: String) {
        sharedPrefModule.savedDong = value
    }
}