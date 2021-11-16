package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.model.Login
import com.example.newernews.domain.model.LoginResponse
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<Login, Response<LoginResponse>>() {
    override fun implement(param: Login): Single<Response<LoginResponse>> {
        return remoteRepository.postLogin(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}