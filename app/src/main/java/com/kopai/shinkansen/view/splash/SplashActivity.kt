package com.kopai.shinkansen.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.local.pref.UserPreference
import com.kopai.shinkansen.data.local.pref.dataStore
import com.kopai.shinkansen.databinding.ActivitySplashBinding
import com.kopai.shinkansen.view.authentication.login.LoginActivity
import com.kopai.shinkansen.view.main.MainActivity
import com.kopai.shinkansen.view.welcome.WelcomeActivity
import java.util.Timer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashViewModel by viewModels<SplashViewModel> {
        SplashViewModel.Factory(UserPreference.getInstance((dataStore)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Timer().schedule(2000) {
            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
            finish()
        }

        splashViewModel.getUserPreferences()
            .observe(this) { userPrefModel ->
                if (userPrefModel.token.isNotEmpty()) Timer().schedule(2000) {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                } else Timer().schedule(2000) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
    }
}