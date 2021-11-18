package com.paasta.newernews.presentation.ui.mainfragment.hotsearch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.paasta.newernews.databinding.FragmentHotSearchBinding
import com.paasta.newernews.presentation.ui.WebViewActivity
import com.paasta.newernews.presentation.ui.mainfragment.hotsearch.adapter.HotLocationAdapter
import com.paasta.newernews.presentation.ui.mainfragment.hotsearch.adapter.HotNewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotSearchFragment : Fragment() {

    private val hotSearchViewModel: HotSearchViewModel by viewModels()

    private var _binding: FragmentHotSearchBinding? = null
    private val binding get() = _binding!!

    private val hotLocationAdapter by lazy { HotLocationAdapter() }
    private val hotNewsAdapter by lazy { HotNewsAdapter(hotSearchViewModel.newsOpenDelegate) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotSearchBinding.inflate(inflater, container, false)

        binding.rvHotLocation.also {
            it.adapter = hotLocationAdapter
            it.layoutManager = GridLayoutManager(this.context, 5, GridLayoutManager.HORIZONTAL, false)
        }

        binding.rvHotNews.also {
            it.adapter = hotNewsAdapter
            it.layoutManager = LinearLayoutManager(this.context)
        }

        hotSearchViewModel.requestHotLocations()
        hotSearchViewModel.hotLocationsLiveData.observe(viewLifecycleOwner, {
            hotLocationAdapter.submitList(it.gus.toMutableList())
            binding.progressBarHotLocations.visibility = View.GONE
        })

        hotSearchViewModel.requestHotNewsList()
        hotSearchViewModel.newsListLiveData.observe(viewLifecycleOwner, {
            hotNewsAdapter.submitList(it.news.toMutableList())
            binding.progressBarHotNews.visibility = View.GONE
        })

        hotSearchViewModel.newsOpenDelegate.urlLiveDelegate.observe(viewLifecycleOwner, {
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