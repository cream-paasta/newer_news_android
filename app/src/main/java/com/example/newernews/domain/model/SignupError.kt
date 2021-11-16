package com.example.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class SignupError (
    @SerializedName("errors")
    var errors: Errors
)

data class Errors (
    @SerializedName("email")
    var email: ArrayList<String>,

    @SerializedName("password")
    var password: ArrayList<String>
)