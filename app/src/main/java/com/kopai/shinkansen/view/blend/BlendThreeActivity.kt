package com.kopai.shinkansen.view.blend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendReviewBinding
import com.kopai.shinkansen.databinding.ActivityBlendThreeBinding

class BlendThreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlendThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}