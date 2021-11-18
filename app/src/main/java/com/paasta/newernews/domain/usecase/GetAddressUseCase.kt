package com.paasta.newernews.domain.usecase

import com.paasta.newernews.data.repository.LocationRepository
import com.paasta.newernews.domain.model.KoreanAddress
import com.paasta.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    private val locationRepository: LocationRepository
): SingleUseCase<Any, KoreanAddress>() {
    override fun implement(param: Any): Single<KoreanAddress> {
        return locationRepository.getLocationAddress()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}