package com.kopai.shinkansen.view.product.productdetails

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.databinding.ActivityProductDetailsBinding
import com.kopai.shinkansen.view.checkout.CheckoutActivity

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mtProductDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val productData =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra<ProductItem>(EXTRA_STORY, ProductItem::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<ProductItem>(EXTRA_STORY)
            }

        binding.btnDetailBuy.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        setDetailProduct(productData)
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

    private fun setDetailProduct(product: ProductItem?) {
        product?.let {
            with(binding) {
                Glide.with(this@ProductDetailsActivity)
                    .load(it.photoUrl)
                    .into(ivDetailPhoto)
                tvDetailName.text = it.name
                tvDetailDescriptionContent.text = it.description
            }
        }
    }

    companion object {
        const val EXTRA_STORY = "extra_product"
    }
}
