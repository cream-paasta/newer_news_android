package com.example.newernews.presentation.ui.mainfragment.hotsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newernews.databinding.FragmentHotSearchBinding

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
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        hotSearchViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}