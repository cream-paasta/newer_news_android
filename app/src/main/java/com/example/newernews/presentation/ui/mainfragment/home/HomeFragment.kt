package com.example.newernews.presentation.ui.mainfragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newernews.R
import com.example.newernews.databinding.FragmentHomeBinding
import com.example.newernews.presentation.ui.mainfragment.home.adapter.HomeNewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val smallLocationAdapter by lazy { HomeNewsAdapter(0) }
    private val bigLocationAdapter by lazy { HomeNewsAdapter(1) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val currentAddress = homeViewModel.getKoreanAddress()

        binding.tvHello.text = String.format(getString(R.string.home_info_hello), "test")

        binding.rvSmallLocationNews.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = smallLocationAdapter
        }

        binding.rvBigLocationNews.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = bigLocationAdapter
        }

        val mutableList = arrayListOf<String>("1", "2", "3", "4", "5")
        smallLocationAdapter.submitList(mutableList)
        bigLocationAdapter.submitList(mutableList)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}