package com.kopai.shinkansen.view.authentication.preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityPreferencesFiveBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesFourBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesOneBinding
import com.kopai.shinkansen.view.authentication.login.LoginViewModel
import com.kopai.shinkansen.view.authentication.recovery.RecoveryAccountActivity
import com.kopai.shinkansen.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

class PreferencesOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesOneBinding.inflate(layoutInflater)
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