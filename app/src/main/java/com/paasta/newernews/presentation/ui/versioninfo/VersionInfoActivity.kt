package com.paasta.newernews.presentation.ui.versioninfo

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paasta.newernews.databinding.ActivityVersionInfoBinding

class VersionInfoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityVersionInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVersionInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var versionName = ""
        try {
            versionName = this.applicationContext.packageManager.getPackageInfo(this.applicationContext.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        binding.tvVersionName.text = "버전: $versionName"
    }
}