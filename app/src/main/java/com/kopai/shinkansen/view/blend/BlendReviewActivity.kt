package com.kopai.shinkansen.view.blend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendOneBinding
import com.kopai.shinkansen.databinding.ActivityBlendReviewBinding

class BlendReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlendReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}