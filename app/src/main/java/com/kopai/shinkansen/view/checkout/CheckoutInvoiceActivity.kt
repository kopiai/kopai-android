package com.kopai.shinkansen.view.checkout

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.databinding.ActivityCheckoutInvoiceBinding

class CheckoutInvoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutInvoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mtCheckoutInvoice)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    private fun setupView() {
        binding.apply {
        }
    }
}
