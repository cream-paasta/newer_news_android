package com.example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewerNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}