package com.paasta.newernews.domain.usecase

import com.paasta.newernews.data.repository.RemoteRepository
import com.paasta.newernews.domain.model.HotLocations
import com.paasta.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetHotLocationsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<String, HotLocations>() {
    override fun implement(param: String): Single<HotLocations> {
        return remoteRepository.getHotLocations(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}