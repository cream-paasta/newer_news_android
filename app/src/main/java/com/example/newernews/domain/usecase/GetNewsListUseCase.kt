package com.example.newernews.domain.usecase

import com.example.newernews.data.repository.RemoteRepository
import com.example.newernews.domain.model.NewsList
import com.example.newernews.domain.model.RequestNewsListModel
import com.example.newernews.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<RequestNewsListModel, NewsList>() {
    override fun implement(param: RequestNewsListModel): Single<NewsList> {
        return remoteRepository.getNewsList(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}