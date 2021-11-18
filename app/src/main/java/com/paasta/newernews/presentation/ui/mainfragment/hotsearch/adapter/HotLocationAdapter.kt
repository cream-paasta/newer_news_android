package com.paasta.newernews.presentation.ui.mainfragment.hotsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paasta.newernews.databinding.ItemHotLocationBinding
import com.paasta.newernews.domain.model.Gus

class HotLocationAdapter(

): ListAdapter<Gus, HotLocationAdapter.ViewHolder>(HotLocationDiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemHotLocationBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Gus, position: Int) {
            binding.gu = item
            binding.tvHotLocationGrade.text = (position+1).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHotLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}