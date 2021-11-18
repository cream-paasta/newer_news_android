package com.paasta.newernews.presentation.ui.mainfragment.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paasta.newernews.R
import com.paasta.newernews.databinding.ItemChatBinding
import com.paasta.newernews.domain.model.Posts

class ChatAdapter(
): ListAdapter<Posts, ChatAdapter.ViewHolder>(ChatDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemChatBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Posts) {
            binding.chat = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_chat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}