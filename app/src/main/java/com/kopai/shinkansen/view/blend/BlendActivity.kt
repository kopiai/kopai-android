package com.kopai.shinkansen.view.blend

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityBlendBinding

class BlendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mtBlend)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
}
