package com.paasta.newernews.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("weather")
    var weather: List<Weather>,

    @SerializedName("main")
    var main: Main,

    @SerializedName("wind")
    var wind: Wind,

    @SerializedName("clouds")
    var clouds: Clouds,

    @SerializedName("sys")
    var sys: Sys
)

data class Weather(
    @SerializedName("id")
    var id: Int,

    @SerializedName("main")
    var weatherTitle: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("icon")
    var icon: String
)

data class Main(
    @SerializedName("temp")
    var temp: Long,

    @SerializedName("feels_like")
    var feelsLikeTemp: Long,

    @SerializedName("temp_min")
    var minTemp: Long,

    @SerializedName("temp_max")
    var maxTemp: Long,

    @SerializedName("humidity")
    var humidity: Int
)

data class Wind(
    @SerializedName("speed")
    var speed: Long,

    @SerializedName("deg")
    var deg: Long
)

data class Clouds(
    @SerializedName("all")
    var cloudPercentage: Int
)

data class Sys(
    @SerializedName("type")
    var type: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("message")
    var message: Long,

    @SerializedName("country")
    var country: String,
)

