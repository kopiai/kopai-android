package com.kopai.shinkansen.view.authentication.preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityPreferencesFiveBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesFourBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesThreeBinding
import com.kopai.shinkansen.view.main.MainActivity

class PreferencesThreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnYes.setOnClickListener {
            // upload preferences
            startActivity(Intent(this, PreferencesTwoActivity::class.java))
        }

        binding.btnNo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}