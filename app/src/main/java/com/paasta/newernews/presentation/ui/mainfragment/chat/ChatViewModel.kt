package com.paasta.newernews.presentation.ui.mainfragment.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paasta.newernews.domain.model.ChatList
import com.paasta.newernews.domain.model.request.Post
import com.paasta.newernews.domain.model.request.PostChat
import com.paasta.newernews.domain.model.request.PostChatModel
import com.paasta.newernews.domain.model.request.RequestGetChatModel
import com.paasta.newernews.domain.usecase.GetChatListUseCase
import com.paasta.newernews.domain.usecase.PostChatUseCase
import com.paasta.newernews.domain.usecase.local.GetSavedGuUseCase
import com.paasta.newernews.domain.usecase.local.GetTokenUseCase
import com.paasta.newernews.domain.usecase.local.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getSavedGuUseCase: GetSavedGuUseCase,
    private val getChatListUseCase: GetChatListUseCase,
    private val postChatUseCase: PostChatUseCase
): ViewModel() {
    private val _chatListLiveData = MutableLiveData<ChatList>()
    val chatListLiveData: LiveData<ChatList> get() = _chatListLiveData

    fun requestChatList() {
        val requestChatModel = RequestGetChatModel(getTokenUseCase.getToken()!!, getSavedGuUseCase.getSavedGu()!!)
        getChatListUseCase.execute(requestChatModel)
            .subscribe(object: SingleObserver<ChatList> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: ChatList?) {
                    if (t != null) _chatListLiveData.value = t!!
                }

                override fun onError(e: Throwable?) {
                    Log.d("TESTLOG", "[requestChatList] onError: $e")
                }
            })
    }

    fun postChat(content: String) {
        val postChat = PostChat(getTokenUseCase.getToken()!!, PostChatModel(Post(content, getSavedGuUseCase.getSavedGu()!!)))
        postChatUseCase.execute(postChat)
            .subscribe(object: SingleObserver<Any> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Any?) {
                    requestChatList()
                }

                override fun onError(e: Throwable?) {
                    Log.e("TESTLOG", "[postChat] onError: $e")
                }
            })
    }
}