package com.kopai.shinkansen.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityMainBinding
import com.kopai.shinkansen.view.blend.BlendOneActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.getSession().observe(this) { user ->
//            if (!user.isLogin) {
//                startActivity(Intent(this, WelcomeActivity::class.java))
//                finish()
//            }
//        }

        val navController = findNavController(R.id.nhf_main)
        binding.bnvMain.setupWithNavController(navController)

        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_beans -> {
                    startActivity(Intent(this, BlendOneActivity::class.java))
                    false
                }
                else -> {
                    navController.navigate(it.itemId)
                    true
                }
            }
        }

    }
}