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
class HateDelegate @Inject constructor(
    private val putLikeUseCase: PutLikeUseCase,
    private val getTokenUseCase: GetTokenUseCase
): ItemClickListener {
    override fun onItemClick(view: View, item: News) {
        item.isBlack = !item.isBlack
        val token: String = getTokenUseCase.getToken()!!
        val requestLikeNewsModel = RequestLikeNewsModel(getTokenUseCase.getToken()!!, item.id, item.isBlack, "black_list")
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

        if (item.isBlack) view.setImageResource(R.drawable.ic_hate_on)
        else view.setImageResource(R.drawable.ic_hate_off)
    }
}