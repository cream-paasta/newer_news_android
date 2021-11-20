package com.paasta.newernews.domain.usecase

import com.paasta.newernews.data.repository.RemoteRepository
import com.paasta.newernews.domain.model.WeatherModel
import com.paasta.newernews.domain.model.request.RequestGetCurrentWeather
import com.paasta.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<RequestGetCurrentWeather, WeatherModel>() {
    override fun implement(param: RequestGetCurrentWeather): Single<WeatherModel> {
        return remoteRepository.getCurrentWeather(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}