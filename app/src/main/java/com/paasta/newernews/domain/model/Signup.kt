package com.paasta.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class Signup (
    @SerializedName("user")
    var user: User
)

data class User (
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("username")
    var username: String
)