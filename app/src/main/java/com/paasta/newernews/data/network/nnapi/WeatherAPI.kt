package com.paasta.newernews.data.network.nnapi

import com.paasta.newernews.domain.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET(BaseUrl.WEATHER_API_GET_WEATHER)
    fun getCurrentWeather(
        @Query("lat") lat: Long,
        @Query("lon") lon: Long,
        @Query("appid") appId: String = BaseUrl.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Single<WeatherModel>
}