package com.paasta.newernews.domain.model.request

data class RequestNewsListModel(
    val auth: String,
    val query: String,
    val hot: Boolean = false
)

data class RequestUserNewsListModel(
    val auth: String,
    val kind: String
)