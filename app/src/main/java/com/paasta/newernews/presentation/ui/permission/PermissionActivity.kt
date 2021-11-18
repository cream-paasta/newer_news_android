package com.paasta.newernews.presentation.ui.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.paasta.newernews.presentation.ui.onboarding.OnboardingActivity

class PermissionActivity: AppCompatActivity() {

    private val PERMISIONS = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION)

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            } else {
                // TODO: 권한 요청 거절 시 앱 종료 안내문구 추가
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val requiredPermission = getRequiredPermission(PERMISIONS)

            if (requiredPermission.isNotEmpty()) requestPermission(requiredPermission)
            else {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        } else finish()
    }

    private fun getRequiredPermission(permissions: Array<String>): Array<String> {
        var requiredPermissions = mutableListOf<String>()

        for (permission: String in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                requiredPermissions.add(permission)
            }
        }

        return requiredPermissions.toTypedArray()
    }

    private fun requestPermission(permissions: Array<String>){
        if (permissions.isNotEmpty()) {
            for (permission: String in permissions) {
                requestPermissionLauncher.launch(permission)
            }
        }
    }
}