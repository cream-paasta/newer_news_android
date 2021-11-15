package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.model.Signup
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<Signup, Any>() {

    override fun implement(param: Signup): Single<Any> {
        return remoteRepository.postSignUp(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}