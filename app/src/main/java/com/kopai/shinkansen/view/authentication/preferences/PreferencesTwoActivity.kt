package com.kopai.shinkansen.view.authentication.preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityPreferencesFiveBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesFourBinding
import com.kopai.shinkansen.databinding.ActivityPreferencesTwoBinding
import com.kopai.shinkansen.view.authentication.login.LoginActivity
import com.kopai.shinkansen.view.main.MainActivity
import com.kopai.shinkansen.view.shared.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import kotlin.concurrent.schedule

@AndroidEntryPoint
class PreferencesTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesTwoBinding

    private val preferencesViewModel: PreferencesViewModel by viewModels()

    private val tokenViewModel: TokenViewModel by viewModels()

    private var answer = ""

    private var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tokenViewModel.token
            .observe(this) { userPrefModel ->
                if (userPrefModel!!.userId.toString().isNotEmpty()) {
                    userId = userPrefModel!!.userId
                }
            }

        val drawable = ContextCompat.getDrawable(this, R.drawable.custom_form_input)

        binding.btnAnswerOne.apply {
            setOnClickListener {
                answer = text.toString()
                background = drawable
            }
        }

        binding.btnAnswerTwo.apply {
            setOnClickListener {
                answer = text.toString()
                background = drawable
            }
        }

        binding.btnAnswerThree.apply {
            setOnClickListener {
                answer = text.toString()
                background = drawable
            }
        }

        binding.btnYes.setOnClickListener {
            // upload preferences
            preferencesViewModel.uploadPreferences(userId,  "", "", "", "")
            startActivity(Intent(this, PreferencesTwoActivity::class.java))
        }

        binding.btnNo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}