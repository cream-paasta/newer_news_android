package com.paasta

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewerNewsApplication : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: NewerNewsApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}