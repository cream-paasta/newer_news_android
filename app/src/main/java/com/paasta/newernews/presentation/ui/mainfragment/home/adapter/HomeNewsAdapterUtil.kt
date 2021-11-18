package com.paasta.newernews.presentation.ui.mainfragment.home.adapter

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.paasta.newernews.domain.model.News

class HomeNewsDiffCallBack(): DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(
        oldItem: News,
        newItem: News
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: News,
        newItem: News
    ): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("bind_title")
fun bindTitle(v: TextView, title: String) {
    v.text = Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT)
}