package com.paasta.newernews.presentation.delegate

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paasta.newernews.domain.model.Gus
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HotLocationDelegate @Inject constructor(
    @ApplicationContext private val context: Context,
): GuItemClickListener {
    private val _guLiveData = MutableLiveData<String>()
    val guLiveDelegate: LiveData<String> get() = _guLiveData

    override fun onItemClick(view: View, item: Gus) {
        _guLiveData.value = item.guName
    }
}