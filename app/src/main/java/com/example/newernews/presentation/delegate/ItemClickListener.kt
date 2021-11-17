package com.example.newernews.presentation.delegate

import com.example.newernews.domain.model.News

interface ItemClickListener {
    fun onItemClick(item: News)
}