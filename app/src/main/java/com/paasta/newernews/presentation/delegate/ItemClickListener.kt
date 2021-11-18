package com.paasta.newernews.presentation.delegate

import android.view.View
import com.paasta.newernews.domain.model.News

interface ItemClickListener {
    fun onItemClick(view: View, item: News)
}