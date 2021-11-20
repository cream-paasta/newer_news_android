package com.paasta.newernews.domain.model

data class KoreanAddress(
    var city: String,
    var gu: String,
    var dong: String,
    var lat: Double = 0.0,
    var lon: Double = 0.0
)