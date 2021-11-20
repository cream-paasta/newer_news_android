package com.paasta.newernews.presentation.ui.mainfragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.paasta.newernews.R
import com.paasta.newernews.databinding.FragmentHomeBinding
import com.paasta.newernews.presentation.ui.WebViewActivity
import com.paasta.newernews.presentation.ui.mainfragment.home.adapter.HomeNewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val smallLocationAdapter by lazy { HomeNewsAdapter(homeViewModel.delegate, 0) }
    private val bigLocationAdapter by lazy { HomeNewsAdapter(homeViewModel.delegate, 1) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.requestUserName()
        homeViewModel.userNameLiveData.observe(viewLifecycleOwner, {
            binding.tvHello.text = String.format(getString(R.string.home_info_hello), it)
        })

        homeViewModel.requestCurrentAddress()
        homeViewModel.currentAddressLiveData.observe(viewLifecycleOwner, {
            binding.tvLocation.text = String.format(getString(R.string.home_info_location), it.city, it.gu, it.dong)
            binding.tvSmallLocationNewsTitle.text = String.format(getString(R.string.home_info_weather), it.gu)
            binding.tvBigLocationNewsTitle.text = String.format(getString(R.string.news_dynamic), it.city)

            homeViewModel.requestNewsList(it.gu, 0)
            homeViewModel.requestNewsList(it.city, 1)
        })

        binding.rvSmallLocationNews.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = smallLocationAdapter
        }

        binding.rvBigLocationNews.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = bigLocationAdapter
        }

        homeViewModel.smallNewsListLivedata.observe(viewLifecycleOwner, {
            it.let {
                smallLocationAdapter.submitList(it.news.toMutableList())
                binding.progressBarSmallNews.visibility = View.GONE
            }
        })

        homeViewModel.bigNewsListLiveData.observe(viewLifecycleOwner, {
            it.let {
                bigLocationAdapter.submitList(it.news.toMutableList())
                binding.progressBarBigNews.visibility = View.GONE
            }
        })

        homeViewModel.delegate.urlLiveDelegate.observe(viewLifecycleOwner, {
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