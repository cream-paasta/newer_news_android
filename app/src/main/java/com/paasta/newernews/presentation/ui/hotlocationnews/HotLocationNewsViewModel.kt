package com.paasta.newernews.presentation.ui.hotlocationnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.model.NewsList
import com.paasta.newernews.domain.model.request.RequestNewsListModel
import com.paasta.newernews.domain.model.request.RequestUserNewsListModel
import com.paasta.newernews.domain.usecase.GetImageUrlUseCase
import com.paasta.newernews.domain.usecase.GetNewsListUseCase
import com.paasta.newernews.domain.usecase.GetUserNewsListUseCase
import com.paasta.newernews.domain.usecase.local.GetTokenUseCase
import com.paasta.newernews.presentation.delegate.HateDelegate
import com.paasta.newernews.presentation.delegate.LikeDelegate
import com.paasta.newernews.presentation.delegate.NewsOpenDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class HotLocationNewsViewModel @Inject constructor(
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

    fun requestNewsList(gu: String) {
        val token = getTokenUseCase.getToken()

        if (token != null) {
            getNewsListUseCase.execute(RequestNewsListModel(token, gu, true))
                .subscribe(object: SingleObserver<NewsList> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: NewsList?) {
                        if (t!!.news.isNotEmpty()) {
                            tempNewsList = t!!
                            getNewsImageUrl(tempNewsList.news[0].link, 0)
                        } else {
                            _newsListLiveData.value = t!!
                        }
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