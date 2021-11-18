package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.model.IncrementViewCountModel
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetIncrementViewCountUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<IncrementViewCountModel, Any>() {
    override fun implement(param: IncrementViewCountModel): Single<Any> {
        return remoteRepository.getIncrementViewCount(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}