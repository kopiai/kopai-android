package com.kopai.shinkansen.view.authentication.preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityPreferencesFiveBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesFourBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesSixBinding
import com.kopai.shinkansen.view.main.MainActivity

class PreferencesSixActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesSixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesSixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGotoHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }



    }
}