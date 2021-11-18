package com.example.newernews.presentation.delegate

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newernews.domain.model.IncrementViewCountModel
import com.example.newernews.domain.model.News
import com.example.newernews.domain.usecase.GetIncrementViewCountUseCase
import com.example.newernews.domain.usecase.PutLikeUseCase
import com.example.newernews.domain.usecase.local.GetTokenUseCase
import com.example.newernews.presentation.ui.WebViewActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

class NewsOpenDelegate @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getTokenUseCase: GetTokenUseCase,
    private val getIncrementViewCountUseCase: GetIncrementViewCountUseCase
): ItemClickListener {
    private val _urlLiveData = MutableLiveData<String>()
    val urlLiveDelegate: LiveData<String> get() = _urlLiveData

    override fun onItemClick(view: View, item: News) {
        _urlLiveData.value = item.link

        getIncrementViewCountUseCase.execute(IncrementViewCountModel(getTokenUseCase.getToken()!!, item.id))
            .subscribe(object: SingleObserver<Any> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Any?) {
                }

                override fun onError(e: Throwable?) {
                    Log.e("TESTLOG", "[NewsOpenDelegate][onItemClick] onError: $e")
                }
            })
    }
}