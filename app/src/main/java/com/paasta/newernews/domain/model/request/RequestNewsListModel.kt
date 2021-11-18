package com.paasta.newernews.domain.model.request

data class RequestNewsListModel(
    val auth: String,
    val query: String,
    val hot: Boolean = false
)