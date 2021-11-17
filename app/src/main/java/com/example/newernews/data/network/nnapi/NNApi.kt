package com.example.newernews.data.network.nnapi

import com.example.newernews.data.repository.SharedPrefRepository
import com.example.newernews.domain.model.Login
import com.example.newernews.domain.model.LoginResponse
import com.example.newernews.domain.model.NewsList
import com.example.newernews.domain.model.Signup
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface NNApi {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.NN_API_POST_SIGN_UP)
    fun postSignUp(
        @Body signup: Signup
    ): Single<Any>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.NN_API_POST_LOGIN)
    fun postLogin(
        @Body login: Login
    ): Single<Response<LoginResponse>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.NN_API_GET_NEWS)
    fun getNewsList(
        @Header("Authorization") auth: String,
        @Query("query") query: String
    ): Single<NewsList>
}