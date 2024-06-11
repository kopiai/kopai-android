package com.kopai.shinkansen.view.checkout

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutBinding.inflate(layoutInflater) }

    private val viewModel: CheckoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.mtCheckout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        val navController = findNavController(R.id.nhf_checkout)

        with(binding) {
            lCheckoutSteps.lCheckoutStepShipping.setOnClickListener {
                navController.navigate(R.id.navigation_checkout_shipping)
            }
            lCheckoutSteps.lCheckoutStepPayment.setOnClickListener {
                navController.navigate(R.id.navigation_checkout_payment)
            }
            lCheckoutSteps.lCheckoutStepOverview.setOnClickListener {
                navController.navigate(R.id.navigation_checkout_overview)
            }
        }

        viewModel.getCheckoutStatus()
    }
}
