package com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newernews.domain.model.NewsList
import com.example.newernews.domain.model.RequestNewsListModel
import com.example.newernews.domain.usecase.GetImageUrlUseCase
import com.example.newernews.domain.usecase.GetNewsListUseCase
import com.example.newernews.domain.usecase.local.GetSavedCityUseCase
import com.example.newernews.domain.usecase.local.GetSavedDongUseCase
import com.example.newernews.domain.usecase.local.GetSavedGuUseCase
import com.example.newernews.domain.usecase.local.GetTokenUseCase
import com.example.newernews.presentation.delegate.HateDelegate
import com.example.newernews.presentation.delegate.LikeDelegate
import com.example.newernews.presentation.delegate.NewsOpenDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class NewsFeedFragmentItemViewModel @Inject constructor(
    private val getSavedCityUseCase: GetSavedCityUseCase,
    private val getSavedGuUseCase: GetSavedGuUseCase,
    private val getSavedDongUseCase: GetSavedDongUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val getImageUrlUseCase: GetImageUrlUseCase,
    val newsOpenDelegate: NewsOpenDelegate,
    val likeDelegate: LikeDelegate,
    val hateDelegate: HateDelegate
): ViewModel() {
    private val _newsListLiveData = MutableLiveData<NewsList>()
    val newsListLiveData: LiveData<NewsList> get() = _newsListLiveData
    private lateinit var tempNewsList: NewsList

    fun requestNewsList(location: Int) { // 0: Dong News, 1: Gu News, 2: City News
        val token = getTokenUseCase.getToken()
        var query = ""
        when (location) {
            0 -> query = getSavedDongUseCase.getSavedDong()!!
            1 -> query = getSavedGuUseCase.getSavedGu()!!
            2 -> query = getSavedCityUseCase.getSavedCity()!!
        }

        if (token != null) {
            getNewsListUseCase.execute(RequestNewsListModel(token, query))
                .subscribe(object: SingleObserver<NewsList> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: NewsList?) {
                        tempNewsList = t!!
                        getNewsImageUrl(tempNewsList.news[0].link, 0)
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[requestNewsList] onError: $e")
                    }
                })
        }
    }

    fun getNewsImageUrl(url: String, position: Int) {
        getImageUrlUseCase.execute(url)
            .subscribe(object: SingleObserver<String> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: String?) {
                    tempNewsList.news[position].imageUrl = t!!
                    if (position+1 < tempNewsList.news.size) {
                        getNewsImageUrl(tempNewsList.news[position+1].link, position+1)
                        if (position!=0 && position%2 == 0) _newsListLiveData.value = tempNewsList
                    } else {
                        _newsListLiveData.value = tempNewsList
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("TESTLOG", "[getNewsImageUrl] onError: $e")
                    if (position+1 < tempNewsList.news.size) {
                        getNewsImageUrl(tempNewsList.news[position+1].link, position+1)
                        if (position!=0 && position%2 == 0) _newsListLiveData.value = tempNewsList
                    } else {
                        _newsListLiveData.value = tempNewsList
                    }
                }
            })
    }
}