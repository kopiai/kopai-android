package com.kopai.shinkansen.view.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.databinding.ActivitySplashBinding
import com.kopai.shinkansen.view.authentication.login.LoginActivity
import com.kopai.shinkansen.view.authentication.register.RegisterActivity
import com.kopai.shinkansen.view.blend.BlendOneActivity
import com.kopai.shinkansen.view.main.MainActivity
import com.kopai.shinkansen.view.shared.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    private val tokenViewModel: TokenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        tokenViewModel.token
            .observe(this) { userPrefModel ->
                if (userPrefModel!!.token.isNotEmpty()) Timer().schedule(2000) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else Timer().schedule(2000) {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
    }
}
