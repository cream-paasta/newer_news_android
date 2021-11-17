package com.example.newernews.presentation.ui.mainfragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newernews.R
import com.example.newernews.databinding.ItemHomeLocationNewsBinding
import com.example.newernews.domain.model.News
import com.example.newernews.presentation.delegate.ItemClickListener

class HomeNewsAdapter(
    val itemClickListener: ItemClickListener,
    val location: Int   // 0: Small Location, 1: Big Location
): ListAdapter<News, HomeNewsAdapter.ViewHolder>(HomeNewsDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemHomeLocationNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.news = item
            binding.onItemClickListener = itemClickListener
            if (location == 0) {
                binding.ivLayout.setImageResource(R.drawable.layout_small_location_news)
            } else {
                binding.ivLayout.setImageResource(R.drawable.layout_big_location_news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home_location_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}