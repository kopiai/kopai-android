package com.kopai.shinkansen.view.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.databinding.ActivitySplashBinding
import com.kopai.shinkansen.view.welcome.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

//    private val settingViewModel by viewModels<SettingViewModel> {
//        SettingViewModel.Factory(SettingPreferences.getInstance((dataStore)))
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        Timer().schedule(2000) {
            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
            finish()
        }

//        settingViewModel.getUserPreferences(Constant.UserPreferences.UserToken.name)
//            .observe(this) { token ->
//                if (token == Constant.preferenceDefaultValue) Timer().schedule(2000) {
//                    startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
//                    finish()
//                } else Timer().schedule(2000) {
//                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                    finish()
//                }
//            }
    }
}
