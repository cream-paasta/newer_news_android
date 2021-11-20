package com.paasta.newernews.domain.model.request

import com.paasta.newernews.data.network.nnapi.BaseUrl

data class RequestGetCurrentWeather(
    var lat: Long,
    var lon: Long,
    var appId: String = BaseUrl.WEATHER_API_KEY,
    var units: String = "metric"
)