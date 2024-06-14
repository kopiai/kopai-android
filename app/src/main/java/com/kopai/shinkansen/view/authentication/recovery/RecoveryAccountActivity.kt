package com.kopai.shinkansen.view.authentication.recovery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityLoginBinding
import com.kopai.shinkansen.databinding.ActivityRecoveryAccountBinding

class RecoveryAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoveryAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSend.setOnClickListener {
            val email = binding.edRecoveryEmail.text.toString()



            startActivity(Intent(this, RecoveryNotifActivity::class.java))
            finish()
        }
    }
}