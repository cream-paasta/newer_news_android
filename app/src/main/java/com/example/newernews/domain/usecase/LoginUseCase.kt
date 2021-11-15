package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.model.Login
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<Login, Any>() {
    override fun implement(param: Login): Single<Any> {
        return remoteRepository.postLogin(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}