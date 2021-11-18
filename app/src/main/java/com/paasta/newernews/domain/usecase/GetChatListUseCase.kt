package com.paasta.newernews.domain.usecase

import com.paasta.newernews.data.repository.RemoteRepository
import com.paasta.newernews.domain.model.ChatList
import com.paasta.newernews.domain.model.request.RequestGetChatModel
import com.paasta.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetChatListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<RequestGetChatModel, ChatList>() {
    override fun implement(param: RequestGetChatModel): Single<ChatList> {
        return remoteRepository.getChatList(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}