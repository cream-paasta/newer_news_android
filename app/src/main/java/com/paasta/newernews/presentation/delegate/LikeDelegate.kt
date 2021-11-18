package com.paasta.newernews.presentation.delegate

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.paasta.newernews.R
import com.paasta.newernews.domain.model.News
import com.paasta.newernews.domain.model.request.RequestLikeNewsModel
import com.paasta.newernews.domain.usecase.PutLikeUseCase
import com.paasta.newernews.domain.usecase.local.GetTokenUseCase
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeDelegate @Inject constructor(
    private val putLikeUseCase: PutLikeUseCase,
    private val getTokenUseCase: GetTokenUseCase
): ItemClickListener {

    override fun onItemClick(view: View, item: News) {
        item.isScrap = !item.isScrap
        val token: String = getTokenUseCase.getToken()!!
        val requestLikeNewsModel = RequestLikeNewsModel(getTokenUseCase.getToken()!!, item.id, item.isScrap, "scrap")
        view as ImageView

        putLikeUseCase.execute(requestLikeNewsModel)
            .subscribe(object: SingleObserver<Any> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Any?) {
                }

                override fun onError(e: Throwable?) {
                    Log.e("TESTLOG", "[LikeDelegate][onItemClick] onError: $e")
                }
            })

        if (item.isScrap) view.setImageResource(R.drawable.ic_like_on)
        else view.setImageResource(R.drawable.ic_like_off)
    }
}