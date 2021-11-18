package com.example.newernews.presentation.ui.mainfragment.newsfeed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newernews.domain.model.KoreanAddress
import com.example.newernews.domain.usecase.local.GetSavedCityUseCase
import com.example.newernews.domain.usecase.local.GetSavedDongUseCase
import com.example.newernews.domain.usecase.local.GetSavedGuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
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