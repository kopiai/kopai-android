package com.kopai.shinkansen.view.blend

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.databinding.ActivityBlendReviewBinding
import com.kopai.shinkansen.databinding.ActivityBlendTwoBinding
import com.kopai.shinkansen.util.Constant
import com.kopai.shinkansen.util.SpinnerItemImage
import com.kopai.shinkansen.view.adapter.SpinnerImageAdapter
import com.kopai.shinkansen.view.product.productdetails.ProductDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlendTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlendTwoBinding

    private var imageBlendTwo: String = ""
    private var nameBlendTwo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlendTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val blendOneData =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_ONE, SpinnerItemImage::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<SpinnerItemImage>(Constant.EXTRA_DATA_BLEND_ONE)
            }
        val blendWeightOne = intent.getStringExtra(Constant.EXTRA_WEIGHT_BLEND_ONE)

        binding.tvCoffee.text = blendOneData!!.text

        Glide.with(this@BlendTwoActivity)
            .load(blendOneData!!.imageResId)
            .into(binding.ivCoffee)

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, BlendThreeActivity::class.java).run {
                putExtra(Constant.EXTRA_DATA_BLEND_ONE, blendOneData)
                putExtra(Constant.EXTRA_WEIGHT_BLEND_ONE, blendWeightOne)

                putExtra(Constant.EXTRA_DATA_BLEND_TWO, SpinnerItemImage(imageBlendTwo, nameBlendTwo))
                putExtra(Constant.EXTRA_WEIGHT_BLEND_TWO, binding.edWeight.text.toString())
            }

//            val intent = Intent(this, BlendThreeActivity::class.java)
//            intent.putExtra(Constant.EXTRA_PHOTO_BLEND_ONE, imageBlendOne)
//            intent.putExtra(Constant.EXTRA_NAME_BLEND_ONE, nameBlendOne)
//            intent.putExtra(Constant.EXTRA_WEIGHT_BLEND_ONE, binding.edWeight.text.toString())

            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
        setupSpinnerImage()
    }

    private fun setupSpinnerImage() {
        val items = listOf(
            SpinnerItemImage("https://www.nescafe.com/id/sites/default/files/Mengulik%20Cita%20Rasa%20Kopi%20Aceh%20Gayo%2C%20Jenis%20Kopi%20di%20Indonesia%20yang%20Jadi%20Kopi%20Termahal%20di%20Dunia.jpg", "Kopi Gayo"),
            SpinnerItemImage("https://coday.id/wp-content/uploads/2019/01/artikel_9_kopitoraja_2.jpg", "Kopi Toraja"),
            SpinnerItemImage("https://img.jakpost.net/c/2017/04/17/2017_04_17_25228_1492395137._large.jpg", "Kopi Java"),
            SpinnerItemImage("https://bagustourservice.com/wp-content/uploads/2018/05/BALI-LUWAK-COFFEE.jpg", "Kopi Bali")
        )

        val adapter = SpinnerImageAdapter(this, items)

        binding.spinnerTwo.adapter = adapter

        binding.cardCoffeeTwo.setOnClickListener {
            if (binding.spinnerTwo.visibility == View.GONE) {
                binding.spinnerTwo.visibility = View.VISIBLE
            } else {
                binding.spinnerTwo.visibility = View.GONE
            }
        }

        binding.spinnerTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                imageBlendTwo = selectedItem.imageResId
                nameBlendTwo = selectedItem.text

                Glide.with(this@BlendTwoActivity)
                    .load(selectedItem.imageResId)
                    .into(binding.ivCoffeeTwo)

                binding.tvCoffeeTwo.text = selectedItem.text
                binding.spinnerTwo.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}