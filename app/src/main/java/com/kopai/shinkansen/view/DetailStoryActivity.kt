package com.kopai.shinkansen.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.databinding.ActivityDetailStoryBinding

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val storyData =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<StoryItem>(EXTRA_STORY, StoryItem::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<StoryItem>(EXTRA_STORY)
            }

        storyData?.let {
            Glide.with(this)
                .load(it.photoUrl)
                .into(binding.ivDetailPhoto)
            binding.tvDetailName.text = it.name
            binding.tvDetailDescription.text = it.description
        }
    }

    companion object {
        const val EXTRA_STORY = "extra_story"
    }
}
