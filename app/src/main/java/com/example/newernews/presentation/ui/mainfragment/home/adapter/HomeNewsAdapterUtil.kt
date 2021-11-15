package com.example.newernews.presentation.ui.mainfragment.home.adapter

import androidx.recyclerview.widget.DiffUtil

class HomeNewsDiffCallBack(): DiffUtil.ItemCallback<String>() {
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