package com.paasta.newernews.domain.usecase

import com.paasta.newernews.data.repository.RemoteRepository
import com.paasta.newernews.domain.model.NewsList
import com.paasta.newernews.domain.model.request.RequestUserNewsListModel
import com.paasta.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetUserNewsListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<RequestUserNewsListModel, NewsList>() {
    override fun implement(param: RequestUserNewsListModel): Single<NewsList> {
        return remoteRepository.getUserNewsList(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}