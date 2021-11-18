package com.example.newernews.domain.model

data class RequestLikeNewsModel(
    val auth: String,
    val id: String,
    val flag: Boolean,
    val kind: String
)