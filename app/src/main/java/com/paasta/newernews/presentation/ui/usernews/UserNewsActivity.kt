package com.paasta.newernews.presentation.ui.usernews

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.paasta.newernews.R
import com.paasta.newernews.databinding.ActivityLikeNewsBinding
import com.paasta.newernews.presentation.ui.WebViewActivity
import com.paasta.newernews.presentation.ui.mainfragment.newsfeed.adapter.recycleradapter.NewsFeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserNewsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLikeNewsBinding
    private val userNewsViewModel: UserNewsViewModel by viewModels()

    private val adapter by lazy {
        NewsFeedAdapter(userNewsViewModel.newsOpenDelegate, userNewsViewModel.likeDelegate, userNewsViewModel.hateDelegate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLike = intent.getBooleanExtra("isLike", true)

        binding = ActivityLikeNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isLike) {
            binding.tvUserNewsTitle.text = getString(R.string.my_page_like_news)
            binding.tvEmptyUserNews.text = getString(R.string.empty_like_news)
        } else {
            binding.tvUserNewsTitle.text = getString(R.string.my_page_hate_news)
            binding.tvEmptyUserNews.text = getString(R.string.empty_hate_news)
        }

        binding.rvUserNews.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        userNewsViewModel.requestNewsList(isLike)
        userNewsViewModel.newsListLiveData.observe(this, {
            if (it.news.isNotEmpty()) {
                adapter.submitList(it.news.toMutableList())
            } else {
                binding.tvEmptyUserNews.visibility = View.VISIBLE
            }
            binding.progressBarUserNews.visibility = View.GONE
        })

        userNewsViewModel.newsOpenDelegate.urlLiveDelegate.observe(this, {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", it)
            startActivity(intent)
        })

        supportActionBar?.hide()
    }
}