package com.paasta.newernews.domain.model.request

import com.google.gson.annotations.SerializedName

data class PostChat(
    var auth: String,
    var postChatModel: PostChatModel
)

data class PostChatModel(
    @SerializedName("post")
    var post: Post
)

data class Post(
    @SerializedName("content")
    var content: String,

    @SerializedName("gu")
    var gu: String
)