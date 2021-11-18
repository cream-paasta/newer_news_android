package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetImageUrlUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<String, String>() {
    override fun implement(param: String): Single<String> {
        return remoteRepository.getImageUrl(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}