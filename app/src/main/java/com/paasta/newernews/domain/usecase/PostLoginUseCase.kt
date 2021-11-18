package com.paasta.newernews.domain.usecase

import com.paasta.newernews.data.repository.RemoteRepository
import com.paasta.newernews.domain.model.Login
import com.paasta.newernews.domain.model.LoginResponse
import com.paasta.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<Login, Response<LoginResponse>>() {
    override fun implement(param: Login): Single<Response<LoginResponse>> {
        return remoteRepository.postLogin(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}