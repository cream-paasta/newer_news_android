package com.paasta.newernews.presentation.ui.mainfragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paasta.newernews.R
import com.paasta.newernews.databinding.FragmentChatBinding
import com.paasta.newernews.presentation.ui.mainfragment.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment: Fragment() {

    private val chatViewModel: ChatViewModel by viewModels()

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val chatAdapter by lazy { ChatAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.tvChatTitle.text = String.format(getString(R.string.chat_title), chatViewModel.getSavedGu())

        binding.rvChat.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
            it.adapter = chatAdapter
        }

        binding.btnSend.setOnClickListener { _ ->
            if (binding.etChat.text.toString().isNotEmpty() && binding.etChat.text.toString().isNotBlank()) {
                chatViewModel.postChat(binding.etChat.text.toString())
                binding.etChat.text = null
            }
        }

        binding.swipeRefreshLayoutChat.setOnRefreshListener { ->
            chatViewModel.requestChatList()
        }

        chatViewModel.requestChatList()
        chatViewModel.chatListLiveData.observe(viewLifecycleOwner, {
            val list = it.posts.toMutableList()
            list.reverse()
            chatAdapter.submitList(list)
            binding.swipeRefreshLayoutChat.isRefreshing = false
            binding.progressBarChat.visibility = View.GONE
        })

        chatAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                (binding.rvChat.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(positionStart, 0)
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}