package com.paasta.newernews.presentation.ui.mainfragment.hotsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.paasta.newernews.databinding.FragmentHotSearchBinding

class HotSearchFragment : Fragment() {

    private lateinit var hotSearchViewModel: HotSearchViewModel
    private var _binding: FragmentHotSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hotSearchViewModel =
            ViewModelProvider(this).get(HotSearchViewModel::class.java)

        _binding = FragmentHotSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}