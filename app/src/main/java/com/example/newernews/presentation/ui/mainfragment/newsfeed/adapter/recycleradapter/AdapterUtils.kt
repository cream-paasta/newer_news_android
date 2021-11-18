package com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter.recycleradapter

import android.content.Context
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.newernews.R
import com.example.newernews.domain.model.News
import com.example.newernews.presentation.ui.TimeUtil
import dagger.hilt.android.qualifiers.ApplicationContext

class NewsFeedDiffCallBack(): DiffUtil.ItemCallback<News>() {
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

@BindingAdapter("bind_news_feed_thumbnail")
fun bindThumbnail(v: ImageView, url: String?) {
    Glide.with(v.context).load(url).into(v)
}

@BindingAdapter("bind_news_feed_title")
fun bindNewsFeedTitle(v: TextView, title: String) {
    v.text = Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("bind_news_feed_time")
fun bindNewsFeedTime(v: TextView, dateTime: String) {
    v.text = TimeUtil.timeStampString(v.context, dateTime)
}

@BindingAdapter("bind_news_feed_like")
fun bindNewsFeedLike(v: ImageView, isScrap: Boolean) {
    if (isScrap) v.setImageResource(R.drawable.ic_like_on)
    else v.setImageResource(R.drawable.ic_like_off)
}

@BindingAdapter("bind_news_feed_hate")
fun bindNewsFeedHate(v: ImageView, isBlack: Boolean) {
    if (isBlack) v.setImageResource(R.drawable.ic_hate_on)
    else v.setImageResource(R.drawable.ic_hate_off)
}
