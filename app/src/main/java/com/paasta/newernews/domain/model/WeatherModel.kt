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

    @SerializedName("rain")
    var rain: Rain,

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
    var temp: Double,

    @SerializedName("feels_like")
    var feelsLikeTemp: Double,

    @SerializedName("temp_min")
    var minTemp: Double,

    @SerializedName("temp_max")
    var maxTemp: Double,

    @SerializedName("humidity")
    var humidity: Int
)

data class Wind(
    @SerializedName("speed")
    var speed: Double,

    @SerializedName("deg")
    var deg: Double
)

data class Clouds(
    @SerializedName("all")
    var cloudPercentage: Int
)

data class Rain(
    @SerializedName("rain.1h")
    var rain1h: Int,

    @SerializedName("rain.3h")
    var rain3h: Int
)

data class Sys(
    @SerializedName("type")
    var type: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("country")
    var country: String,

    @SerializedName("sunrise")
    var sunRise: Int,

    @SerializedName("sunset")
    var sunSet: Int
)

