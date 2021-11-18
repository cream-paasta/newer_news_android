package com.paasta.newernews.presentation.ui.mainfragment.newsfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.model.KoreanAddress
import com.paasta.newernews.domain.usecase.local.GetSavedCityUseCase
import com.paasta.newernews.domain.usecase.local.GetSavedDongUseCase
import com.paasta.newernews.domain.usecase.local.GetSavedGuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val getSavedCityUseCase: GetSavedCityUseCase,
    private val getSavedGuUseCase: GetSavedGuUseCase,
    private val getSavedDongUseCase: GetSavedDongUseCase
) : ViewModel() {
    private val _savedAddressLiveData = MutableLiveData<KoreanAddress>()
    val savedAddressLiveData: LiveData<KoreanAddress> get() = _savedAddressLiveData

    fun getSavedAddress() {
        _savedAddressLiveData.value = KoreanAddress(
            getSavedCityUseCase.getSavedCity()!!,
            getSavedGuUseCase.getSavedGu()!!,
            getSavedDongUseCase.getSavedDong()!!)
    }
}