package com.kopai.shinkansen.view.news

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.NewsResponseItem
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.databinding.ActivityDetailNewsBinding
import com.kopai.shinkansen.view.product.productdetails.ProductDetailsActivity

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsData =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<NewsResponseItem>(DetailNewsActivity.EXTRA_NEWS, NewsResponseItem::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<NewsResponseItem>(DetailNewsActivity.EXTRA_NEWS)
            }

        setDetailNews(newsData)
    }

    private fun setDetailNews(news: NewsResponseItem?) {
        news?.let {
            with(binding) {
                Glide.with(this@DetailNewsActivity)
                    .load(it.picture)
                    .into(ivImageDetail)
                tvNameDetail.text = it.newsTitle
                tvAuthor.text = it.newsAuthor
                tvDescription.text = it.description
            }
        }
    }

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }
}