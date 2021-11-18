package com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newernews.databinding.FragmentNewsFeedItemBinding
import com.example.newernews.presentation.ui.WebViewActivity
import com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter.recycleradapter.NewsFeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedFragmentItem(
    private val position: Int
): Fragment() {
    private val newsFeedFragmentItemViewModel: NewsFeedFragmentItemViewModel by viewModels()

    private var _binding: FragmentNewsFeedItemBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        NewsFeedAdapter(newsFeedFragmentItemViewModel.newsOpenDelegate, newsFeedFragmentItemViewModel.likeDelegate, newsFeedFragmentItemViewModel.hateDelegate)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsFeedItemBinding.inflate(inflater, container, false)

        newsFeedFragmentItemViewModel.requestNewsList(position)

        newsFeedFragmentItemViewModel.newsListLiveData.observe(viewLifecycleOwner, {
            adapter.submitList(it.news.toMutableList())
            binding.progressBarNewsFeed.visibility = View.GONE
        })

        binding.rvNewsList.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = adapter
        }

        newsFeedFragmentItemViewModel.newsOpenDelegate.urlLiveDelegate.observe(viewLifecycleOwner, {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("url", it)
            startActivity(intent)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}