package com.example.newernews.presentation.delegate

import android.view.View
import com.example.newernews.domain.model.News

interface ItemClickListener {
    fun onItemClick(view: View, item: News)
}