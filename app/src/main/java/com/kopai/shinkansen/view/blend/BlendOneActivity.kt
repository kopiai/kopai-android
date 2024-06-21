package com.kopai.shinkansen.view.blend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendOneBinding
import com.kopai.shinkansen.databinding.ActivityRegisterBinding
import com.kopai.shinkansen.util.Constant
import com.kopai.shinkansen.util.SpinnerItemImage
import com.kopai.shinkansen.view.adapter.SpinnerImageAdapter
import com.kopai.shinkansen.view.authentication.register.RegisterViewModel
import com.kopai.shinkansen.view.product.productdetails.ProductDetailsActivity
import com.kopai.shinkansen.view.profile.editprofile.EditProfileActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlendOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlendOneBinding

    private val blendViewModel: BlendViewModel by viewModels()

    private var imageBlendOne: String = ""
    private var nameBlendOne: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinnerImage()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, BlendTwoActivity::class.java).run {
                putExtra(Constant.EXTRA_DATA_BLEND_ONE, SpinnerItemImage(imageBlendOne, nameBlendOne))
                putExtra(Constant.EXTRA_WEIGHT_BLEND_ONE, binding.edWeight.text.toString())
            }
//            val intent = Intent(this, BlendTwoActivity::class.java)
//            intent.putExtra(Constant.EXTRA_PHOTO_BLEND_ONE, imageBlendOne)
//            intent.putExtra(Constant.EXTRA_NAME_BLEND_ONE, nameBlendOne)
//            intent.putExtra(Constant.EXTRA_WEIGHT_BLEND_ONE, binding.edWeight.text.toString())
            startActivity(intent)
        }

        binding.btnSingleOrigin.setOnClickListener {
            val intent = Intent(this, BlendThreeActivity::class.java).run {
                putExtra(Constant.EXTRA_DATA_BLEND_ONE, SpinnerItemImage(imageBlendOne, nameBlendOne))
                putExtra(Constant.EXTRA_WEIGHT_BLEND_ONE, binding.edWeight.text.toString())
            }
//            val intent = Intent(this, BlendThreeActivity::class.java)
//            intent.putExtra(Constant.EXTRA_PHOTO_BLEND_ONE, imageBlendOne)
//            intent.putExtra(Constant.EXTRA_NAME_BLEND_ONE, nameBlendOne)
//            intent.putExtra(Constant.EXTRA_WEIGHT_BLEND_ONE, binding.edWeight.text.toString())

            startActivity(intent)
        }

    }

    private fun setupSpinnerImage() {
        val items = listOf(
            SpinnerItemImage("https://www.nescafe.com/id/sites/default/files/Mengulik%20Cita%20Rasa%20Kopi%20Aceh%20Gayo%2C%20Jenis%20Kopi%20di%20Indonesia%20yang%20Jadi%20Kopi%20Termahal%20di%20Dunia.jpg", "Kopi Gayo"),
            SpinnerItemImage("https://coday.id/wp-content/uploads/2019/01/artikel_9_kopitoraja_2.jpg", "Kopi Toraja"),
            SpinnerItemImage("https://img.jakpost.net/c/2017/04/17/2017_04_17_25228_1492395137._large.jpg", "Kopi Java"),
            SpinnerItemImage("https://bagustourservice.com/wp-content/uploads/2018/05/BALI-LUWAK-COFFEE.jpg", "Kopi Bali")
        )

        val adapter = SpinnerImageAdapter(this, items)

        binding.spinner.adapter = adapter

        binding.cardCoffee.setOnClickListener {
            if (binding.spinner.visibility == View.GONE) {
                binding.spinner.visibility = View.VISIBLE
            } else {
                binding.spinner.visibility = View.GONE
            }
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                imageBlendOne = selectedItem.imageResId
                nameBlendOne = selectedItem.text

                Glide.with(this@BlendOneActivity)
                    .load(selectedItem.imageResId)
                    .into(binding.ivCoffee)

                binding.tvCoffee.text = selectedItem.text
                binding.spinner.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

    }
}