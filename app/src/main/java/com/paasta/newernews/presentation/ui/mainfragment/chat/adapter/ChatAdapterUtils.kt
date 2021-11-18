package com.paasta.newernews.presentation.ui.mainfragment.chat.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.paasta.newernews.domain.model.Posts
import com.paasta.newernews.presentation.ui.TimeUtil

class ChatDiffCallBack(): DiffUtil.ItemCallback<Posts>() {
    override fun areItemsTheSame(
        oldItem: Posts,
        newItem: Posts
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Posts,
        newItem: Posts
    ): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("bind_chat_time")
fun bindChatTime(v: TextView, dateTime: String?) {
    if(dateTime != null) v.text = TimeUtil.timeStampString(v.context, dateTime)
}