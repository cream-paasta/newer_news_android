package com.paasta.newernews.presentation.ui.mainfragment.newsfeed.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paasta.newernews.R
import com.paasta.newernews.databinding.ItemNewsFeedBinding
import com.paasta.newernews.domain.model.News
import com.paasta.newernews.presentation.delegate.ItemClickListener

class NewsFeedAdapter(
    val itemClickListener: ItemClickListener,
    val likeClickListener: ItemClickListener,
    val hateClickListener: ItemClickListener
): ListAdapter<News, NewsFeedAdapter.ViewHolder>(NewsFeedDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemNewsFeedBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.news = item
            binding.onItemClickListener = itemClickListener
            binding.onLikeClickListener = likeClickListener
            binding.onHateClickListener = hateClickListener
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_news_feed, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}