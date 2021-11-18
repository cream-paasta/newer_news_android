package com.paasta.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class ChatList(
    @SerializedName("posts")
    var posts: List<Posts>
)

data class Posts(
    @SerializedName("id")
    var id: Int,

    @SerializedName("content")
    var content: String,

    @SerializedName("user_name")
    var userName: String,

    @SerializedName("created_at")
    var createdTime: String
)