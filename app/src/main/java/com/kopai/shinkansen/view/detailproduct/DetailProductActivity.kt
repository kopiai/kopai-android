package com.kopai.shinkansen.view.detailproduct

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.databinding.ActivityDetailProductBinding
import com.kopai.shinkansen.view.checkout.CheckoutActivity

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mtDetailProduct)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val storyData =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<StoryItem>(EXTRA_STORY, StoryItem::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<StoryItem>(EXTRA_STORY)
            }

        binding.btnDetailBuy.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        setDetailProduct(storyData)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDetailProduct(story: StoryItem?) {
        story?.let {
            with(binding) {
                Glide.with(this@DetailProductActivity)
                    .load(it.photoUrl)
                    .into(ivDetailPhoto)
                tvDetailName.text = it.name
                tvDetailDescriptionContent.text = it.description
            }
        }
    }

    companion object {
        const val EXTRA_STORY = "extra_story"
    }
}
