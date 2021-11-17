package com.example.newernews.presentation.delegate

import com.example.newernews.domain.model.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsOpenDelegate @Inject constructor(

): ItemClickListener {
    override fun onItemClick(item: News) {
        // TODO 웹 뷰 연결
    }
}