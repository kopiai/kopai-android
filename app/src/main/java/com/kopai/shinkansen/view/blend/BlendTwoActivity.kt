package com.kopai.shinkansen.view.blend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendReviewBinding
import com.kopai.shinkansen.databinding.ActivityBlendTwoBinding
import com.kopai.shinkansen.util.SpinnerItemImage
import com.kopai.shinkansen.view.adapter.SpinnerImageAdapter

class BlendTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlendTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlendTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinnerImage()
    }

    private fun setupSpinnerImage() {
        val items = listOf(
            SpinnerItemImage(R.drawable.logo, "Item 1"),
            SpinnerItemImage(R.drawable.avatar, "Item 2"),
            SpinnerItemImage(R.drawable.beans, "Item 3")
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
                binding.tvCoffeeTwo.text = selectedItem.text
                binding.ivCoffeeTwo.setImageResource(selectedItem.imageResId)
                binding.spinnerTwo.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}