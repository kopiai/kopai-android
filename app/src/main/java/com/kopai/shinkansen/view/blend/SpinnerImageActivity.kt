package com.kopai.shinkansen.view.blend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivitySpinnerImageBinding
import com.kopai.shinkansen.util.SpinnerItemImage
import com.kopai.shinkansen.view.adapter.SpinnerImageAdapter

class SpinnerImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            SpinnerItemImage(R.drawable.logo, "Item 1"),
            SpinnerItemImage(R.drawable.avatar, "Item 2"),
            SpinnerItemImage(R.drawable.beans, "Item 3")
        )

        val adapter = SpinnerImageAdapter(this, items)
        binding.spinner.adapter = adapter

        binding.cardview.setOnClickListener {
            if (binding.spinner.visibility == View.GONE) {
                binding.spinner.visibility = View.VISIBLE
            } else {
                binding.spinner.visibility = View.GONE
            }
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                binding.cardText.text = selectedItem.text
                binding.cardImage.setImageResource(selectedItem.imageResId)
                binding.spinner.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}