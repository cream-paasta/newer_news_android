package com.paasta.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class Login (
    @SerializedName("user")
    var user: LoginUser
)

data class LoginUser(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String
)