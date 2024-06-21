package com.kopai.shinkansen.view.blend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendOneBinding
import com.kopai.shinkansen.databinding.ActivityBlendReviewBinding
import com.kopai.shinkansen.view.checkout.CheckoutActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlendReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlendReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCoffee.text = "Kopi Gayo"
        Glide.with(this@BlendReviewActivity)
            .load("https://www.nescafe.com/id/sites/default/files/Mengulik%20Cita%20Rasa%20Kopi%20Aceh%20Gayo%2C%20Jenis%20Kopi%20di%20Indonesia%20yang%20Jadi%20Kopi%20Termahal%20di%20Dunia.jpg")
            .into(binding.ivCoffee)

        binding.tvCoffeeTwo.text = "Kopi Toraja"
        Glide.with(this@BlendReviewActivity)
            .load("https://coday.id/wp-content/uploads/2019/01/artikel_9_kopitoraja_2.jpg")
            .into(binding.ivCoffeeTwo)

        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

    }
}