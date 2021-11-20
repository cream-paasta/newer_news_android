package com.paasta.newernews.data.network.nnapi

import com.paasta.newernews.domain.model.*
import com.paasta.newernews.domain.model.request.PostChatModel
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
        @Query("query") query: String,
        @Query("hot") isHot: Boolean
    ): Single<NewsList>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.NN_API_GET_NEWS + "/" + BaseUrl.NN_API_GET_USER_NEWS)
    fun getUserNewsList(
        @Header("Authorization") auth: String,
        @Query("kind") kind: String
    ): Single<NewsList>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.NN_API_GET_NEWS + "/{id}")
        fun getIncrementViewCount(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    ): Single<Any>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @PUT(BaseUrl.NN_API_GET_NEWS + "/{id}/" + BaseUrl.NN_API_PUT_LIKE)
    fun putLikeNews(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Query("flag") flag: Boolean,
        @Query("kind") kind: String
    ): Single<Any>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.NN_API_GET_HOT_LOCATIONS)
    fun getHotLocations(
        @Header("Authorization") auth: String,
    ): Single<HotLocations>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(BaseUrl.NN_API_CHAT)
    fun getChatList(
        @Header("Authorization") auth: String,
        @Query("gu") gu: String
    ): Single<ChatList>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST(BaseUrl.NN_API_CHAT)
    fun postChat(
        @Header("Authorization") auth: String,
        @Body chatModel: PostChatModel
    ): Single<Any>
}