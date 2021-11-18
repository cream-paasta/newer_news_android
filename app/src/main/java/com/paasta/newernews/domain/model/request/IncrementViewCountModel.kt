package com.paasta.newernews.domain.model.request

data class IncrementViewCountModel(
    var auth: String,
    val id: String
)