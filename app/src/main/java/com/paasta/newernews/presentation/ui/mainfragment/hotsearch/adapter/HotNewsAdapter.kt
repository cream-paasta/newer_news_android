package com.paasta.newernews.presentation.ui.mainfragment.hotsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paasta.newernews.R
import com.paasta.newernews.databinding.ItemHotNewsBinding
import com.paasta.newernews.domain.model.News
import com.paasta.newernews.presentation.delegate.ItemClickListener

class HotNewsAdapter(
    val itemClickListener: ItemClickListener
): ListAdapter<News, HotNewsAdapter.ViewHolder>(HotNewsDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemHotNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News, position: Int) {
            binding.news = item
            binding.onItemClickListener = itemClickListener
            binding.tvHotNewsGrade.text = (position+1).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_hot_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}