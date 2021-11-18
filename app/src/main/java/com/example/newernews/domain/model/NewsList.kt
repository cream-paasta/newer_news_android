package com.example.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class NewsList(
    @SerializedName("issues")
    var news: List<News>
)

data class News(
    @SerializedName("id")
    var id: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("link")
    var link: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("view_count")
    var viewCount: Int,

    @SerializedName("pubed_at")
    var postDateTime: String,

    @SerializedName("is_scrap")
    var isScrap: Boolean,

    var isBlack: Boolean = false,

    @SerializedName("scrap_count")
    var scrapCount: Int,

    var imageUrl: String
)