package com.paasta.newernews.presentation.ui.mainfragment.hotsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.model.HotLocations
import com.paasta.newernews.domain.model.NewsList
import com.paasta.newernews.domain.model.request.RequestNewsListModel
import com.paasta.newernews.domain.usecase.GetHotLocationsUseCase
import com.paasta.newernews.domain.usecase.GetNewsListUseCase
import com.paasta.newernews.domain.usecase.local.GetSavedGuUseCase
import com.paasta.newernews.domain.usecase.local.GetTokenUseCase
import com.paasta.newernews.presentation.delegate.NewsOpenDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class HotSearchViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getSavedGuUseCase: GetSavedGuUseCase,
    private val getHotLocationsUseCase: GetHotLocationsUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    val newsOpenDelegate: NewsOpenDelegate
) : ViewModel() {
    private val _newsListLiveData = MutableLiveData<NewsList>()
    val newsListLiveData: LiveData<NewsList> get() = _newsListLiveData

    private val _hotLocationsLiveData = MutableLiveData<HotLocations>()
    val hotLocationsLiveData: LiveData<HotLocations> get() = _hotLocationsLiveData

    fun requestHotLocations() {
        getHotLocationsUseCase.execute(getTokenUseCase.getToken()!!)
            .subscribe(object: SingleObserver<HotLocations> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: HotLocations?) {
                    if (t != null ) _hotLocationsLiveData.value = t!!
                }

                override fun onError(e: Throwable?) {
                    Log.d("TESTLOG", "[requestHotLocations] onError: $e")
                }
            })
    }

    fun requestHotNewsList() {
        val requestNewsListModel = RequestNewsListModel(getTokenUseCase.getToken()!!, getSavedGuUseCase.getSavedGu()!!, true)
        getNewsListUseCase.execute(requestNewsListModel)
            .subscribe(object: SingleObserver<NewsList> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: NewsList?) {
                    if (t != null) _newsListLiveData.value = t!!
                }

                override fun onError(e: Throwable?) {
                    Log.e("TESTLOG", "[requestHotNewsList] onError: $e")
                }
            })
    }
}