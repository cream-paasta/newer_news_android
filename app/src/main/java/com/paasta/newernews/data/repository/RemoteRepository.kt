package com.paasta.newernews.data.repository

import com.paasta.newernews.data.network.nnapi.NNApi
import com.paasta.newernews.domain.model.*
import com.paasta.newernews.domain.model.request.*
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
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
        NNApiClient.getNewsList(param.auth, param.query, param.hot)

    fun getIncrementViewCount(param: IncrementViewCountModel): Single<Any> =
        NNApiClient.getIncrementViewCount(param.auth, param.id)

    fun putLikeNews(param: RequestLikeNewsModel): Single<Any> =
        NNApiClient.putLikeNews(param.auth, param.id, param.flag, param.kind)

    fun getHotLocations(param: String): Single<HotLocations> =
        NNApiClient.getHotLocations(param)

    fun getChatList(param: RequestGetChatModel): Single<ChatList> =
        NNApiClient.getChatList(param.auth, param.gu)

    fun postChat(param: PostChat): Single<Any> =
        NNApiClient.postChat(param.auth, param.postChatModel)

    fun getImageUrl(url: String): Single<String> {
        return Single.create{ emitter ->
            var isSuccess = false
            val ogTags = Jsoup.connect(url).get().select("meta[property^=og:]")

            for (og in ogTags) {
                if (og.attr("property").equals("og:image")) {
                    emitter.onSuccess(og.attr("content"))
                    isSuccess = true
                }
            }
            if (!isSuccess) emitter.onError(Throwable("doesn't have any image link"))
        }
    }
}