package com.kopai.shinkansen.view.blend

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendReviewBinding
import com.kopai.shinkansen.databinding.ActivityBlendThreeBinding
import com.kopai.shinkansen.util.Constant
import com.kopai.shinkansen.util.SpinnerItemImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlendThreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlendThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val blendTwoData : SpinnerItemImage? =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_TWO, SpinnerItemImage::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_TWO)
            }

        if (blendTwoData != null) {
            val blendOneData =
                if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_ONE, SpinnerItemImage::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_ONE)
                }
            binding.tvCoffee.text = blendOneData!!.text
            Glide.with(this@BlendThreeActivity)
                .load(blendOneData!!.imageResId)
                .into(binding.ivCoffee)

            binding.tvCoffeeTwo.text = blendTwoData!!.text
            Glide.with(this@BlendThreeActivity)
                .load(blendTwoData!!.imageResId)
                .into(binding.ivCoffeeTwo)
        } else {
            val blendOneData =
                if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_ONE, SpinnerItemImage::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_ONE)
                }
            binding.tvCoffee.text = blendOneData!!.text
            Glide.with(this@BlendThreeActivity)
                .load(blendOneData!!.imageResId)
                .into(binding.ivCoffee)
        }

        binding.btnBlend.setOnClickListener {
            startActivity(Intent(this, BlendReviewActivity::class.java))
        }

    }
}