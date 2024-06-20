package com.kopai.shinkansen.view.blend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendOneBinding
import com.kopai.shinkansen.databinding.ActivityRegisterBinding
import com.kopai.shinkansen.util.SpinnerItemImage
import com.kopai.shinkansen.view.adapter.SpinnerImageAdapter
import com.kopai.shinkansen.view.authentication.register.RegisterViewModel

class BlendOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlendOneBinding

    private val blendViewModel: BlendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinnerImage()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnContinue.setOnClickListener {

        }

        binding.btnSingleOrigin.setOnClickListener {

        }

    }

    private fun setupSpinnerImage() {
        val items = listOf(
            SpinnerItemImage(R.drawable.logo, "Item 1"),
            SpinnerItemImage(R.drawable.avatar, "Item 2"),
            SpinnerItemImage(R.drawable.beans, "Item 3")
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
                binding.tvCoffee.text = selectedItem.text
                binding.ivCoffee.setImageResource(selectedItem.imageResId)
                binding.spinner.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

    }
}