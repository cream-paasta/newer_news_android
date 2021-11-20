package com.paasta.newernews.presentation.ui.hotlocationnews

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.paasta.newernews.R
import com.paasta.newernews.databinding.ActivityHotLocationNewsBinding
import com.paasta.newernews.presentation.ui.WebViewActivity
import com.paasta.newernews.presentation.ui.mainfragment.newsfeed.adapter.recycleradapter.NewsFeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotLocationNewsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHotLocationNewsBinding
    private val hotLocationNewsViewModel: HotLocationNewsViewModel by viewModels()

    private val adapter by lazy {
        NewsFeedAdapter(hotLocationNewsViewModel.newsOpenDelegate, hotLocationNewsViewModel.likeDelegate, hotLocationNewsViewModel.hateDelegate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gu = intent.getStringExtra("gu")?: ""

        binding = ActivityHotLocationNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvHotLocationNewsTitle.text = String.format(getString(R.string.news_title_format), gu)

        binding.rvHotLocationNews.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        hotLocationNewsViewModel.requestNewsList(gu)
        hotLocationNewsViewModel.newsListLiveData.observe(this, {
            adapter.submitList(it.news.toMutableList())
            binding.progressBarHotNews.visibility = View.GONE
        })

        hotLocationNewsViewModel.newsOpenDelegate.urlLiveDelegate.observe(this, {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", it)
            startActivity(intent)
        })

    }
}