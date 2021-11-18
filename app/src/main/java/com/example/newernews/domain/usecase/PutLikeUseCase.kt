package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.model.RequestLikeNewsModel
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PutLikeUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<RequestLikeNewsModel, Any>() {
    override fun implement(param: RequestLikeNewsModel): Single<Any> {
        return remoteRepository.putLikeNews(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}