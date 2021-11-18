package com.paasta.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class HotLocations(
    @SerializedName("gus")
    var gus: List<Gus>
)

data class Gus(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var guName: String,

    @SerializedName("count")
    var count: Int
)