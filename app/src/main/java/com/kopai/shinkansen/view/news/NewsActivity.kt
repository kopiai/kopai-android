package com.kopai.shinkansen.view.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.ActivityNewsBinding
import com.kopai.shinkansen.databinding.ActivityProductDetailsBinding
import com.kopai.shinkansen.view.adapter.ListNewsAdapter
import com.kopai.shinkansen.view.adapter.NewsAdapter
import com.kopai.shinkansen.view.adapter.ProductAdapter
import com.kopai.shinkansen.view.main.home.MainHomeViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private val viewModel: MainHomeViewModel by viewModels()

    private val newsAdapter by lazy { ListNewsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchNews()
    }

    private fun fetchNews() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getNews().observe(this) {
            when (it) {
                is ResultState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }

                ResultState.Loading -> {
                    Log.d("MainActivity", "Loading fetch banner")
                }

                is ResultState.Success -> {
                    newsAdapter.submitList(it.data.data)
                }
            }
        }
    }
}