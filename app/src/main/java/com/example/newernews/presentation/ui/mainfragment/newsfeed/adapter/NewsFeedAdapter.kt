package com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newernews.databinding.ItemNewsFeedBinding

class NewsFeedAdapter(

): ListAdapter<String, NewsFeedAdapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemNewsFeedBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            // TODO 실제 데이터 들어오면 String에서 Item으로 변경해야함
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}