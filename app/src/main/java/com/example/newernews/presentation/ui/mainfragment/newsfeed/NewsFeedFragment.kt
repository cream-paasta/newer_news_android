package com.example.newernews.presentation.ui.mainfragment.newsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newernews.databinding.FragmentNewsFeedBinding
import com.example.newernews.presentation.ui.mainfragment.newsfeed.adapter.NewsFeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedFragment : Fragment() {

    private val newsFeedViewModel: NewsFeedViewModel by viewModels()

    private var _binding: FragmentNewsFeedBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { NewsFeedAdapter() }
    private val layoutManager = GridLayoutManager(this.context, 2)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvNewsList.also {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        val mutableList = arrayListOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14")
        adapter.submitList(mutableList)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}