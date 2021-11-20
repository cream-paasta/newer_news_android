package com.paasta.newernews.presentation.ui.mainfragment.hotsearch.adapter

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.paasta.newernews.domain.model.Gus
import com.paasta.newernews.domain.model.News
import com.paasta.newernews.presentation.ui.TimeUtil

class HotLocationDiffCallBack(): DiffUtil.ItemCallback<Gus>() {
    override fun areItemsTheSame(
        oldItem: Gus,
        newItem: Gus
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Gus,
        newItem: Gus
    ): Boolean {
        return oldItem == newItem
    }
}

class HotNewsDiffCallBack(): DiffUtil.ItemCallback<News>() {
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

@BindingAdapter("bind_hot_location")
fun bindHotLocation(v: TextView, gu: String) {
    v.text = "서울특별시 $gu"
}

@BindingAdapter("bind_hot_news_title")
fun bindHotNewsTitle(v: TextView, title: String) {
    v.text = Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("bind_hot_news_time")
fun bindHotNewsTime(v: TextView, dateTime: String?) {
    if(dateTime != null) v.text = TimeUtil.timeStampString(v.context, dateTime)
}