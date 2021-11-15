package com.example.newernews.presentation.ui.mainfragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newernews.R
import com.example.newernews.databinding.ItemHomeLocationNewsBinding

class HomeNewsAdapter(
    val location: Int   // 0: Small Location, 1: Big Location
): ListAdapter<String, HomeNewsAdapter.ViewHolder>(HomeNewsDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemHomeLocationNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            // TODO 실제 데이터 들어오면 String에서 Item으로 변경해야함
            if (location == 0) {
                binding.ivLayout.setImageResource(R.drawable.layout_small_location_news)
            } else {
                binding.ivLayout.setImageResource(R.drawable.layout_big_location_news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeLocationNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}