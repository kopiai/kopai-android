package com.kopai.shinkansen.view.authentication.recovery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityRecoveryAccountBinding
import com.kopai.shinkansen.databinding.ActivityRecoveryNotifBinding
import com.kopai.shinkansen.view.authentication.login.LoginActivity

class RecoveryNotifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoveryNotifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}