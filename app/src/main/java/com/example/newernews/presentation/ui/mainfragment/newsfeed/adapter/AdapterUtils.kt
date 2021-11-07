package com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter

import androidx.recyclerview.widget.DiffUtil

class DiffCallBack(): DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }
}