package com.paasta.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("email")
    var email: String,

    @SerializedName("username")
    var userName: String
)