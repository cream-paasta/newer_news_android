package com.example.newernews.data.repository

import com.example.newernews.data.network.nnapi.NNApi
import com.example.newernews.domain.model.Login
import com.example.newernews.domain.model.Signup
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val NNApiClient: NNApi
){
    fun postSignUp(param: Signup): Single<Any> =
        NNApiClient.postSignUp(param)

    fun postLogin(param: Login): Single<Any> =
        NNApiClient.postLogin(param)
}