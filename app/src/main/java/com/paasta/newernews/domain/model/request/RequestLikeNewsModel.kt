package com.paasta.newernews.domain.model.request

data class RequestLikeNewsModel(
    val auth: String,
    val id: String,
    val flag: Boolean,
    val kind: String
)