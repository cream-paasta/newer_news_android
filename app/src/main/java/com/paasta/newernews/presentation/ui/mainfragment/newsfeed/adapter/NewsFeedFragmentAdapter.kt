package com.paasta.newernews.presentation.ui.mainfragment.newsfeed.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class NewsFeedFragmentAdapter(
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return NewsFeedFragmentItem(position)
    }
}