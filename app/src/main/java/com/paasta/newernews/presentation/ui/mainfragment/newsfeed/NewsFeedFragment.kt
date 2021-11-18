package com.paasta.newernews.presentation.ui.mainfragment.newsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paasta.newernews.R
import com.paasta.newernews.databinding.FragmentNewsFeedBinding
import com.paasta.newernews.presentation.ui.mainfragment.newsfeed.adapter.NewsFeedFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedFragment : Fragment() {

    private val newsFeedViewModel: NewsFeedViewModel by viewModels()

    private var _binding: FragmentNewsFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsFeedFragmentAdapter: NewsFeedFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsFeedBinding.inflate(inflater, container, false)

        newsFeedViewModel.getSavedAddress()
        newsFeedViewModel.savedAddressLiveData.observe(viewLifecycleOwner, {
            TabLayoutMediator(binding.newsFeedTabLayout, binding.newsFeedViewPager) { tab, position ->
                when(position) {
                    0 -> tab.text = String.format(getString(R.string.news_dynamic), it.dong)
                    1 -> tab.text = String.format(getString(R.string.news_dynamic), it.gu)
                    2 -> tab.text = String.format(getString(R.string.news_dynamic), it.city)
                }
            }.attach()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsFeedFragmentAdapter = NewsFeedFragmentAdapter(this)
        binding.newsFeedViewPager.adapter = newsFeedFragmentAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}