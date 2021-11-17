package com.example.newernews.data.repository

import com.example.newernews.data.network.nnapi.NNApi
import com.example.newernews.domain.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val NNApiClient: NNApi
){
    fun postSignUp(param: Signup): Single<Any> =
        NNApiClient.postSignUp(param)

    fun postLogin(param: Login): Single<Response<LoginResponse>> =
        NNApiClient.postLogin(param)

    fun getNewsList(param: RequestNewsListModel): Single<NewsList> =
        NNApiClient.getNewsList(param.auth, param.query)
}