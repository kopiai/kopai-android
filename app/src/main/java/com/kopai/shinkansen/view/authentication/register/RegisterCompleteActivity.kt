package com.kopai.shinkansen.view.authentication.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityRegisterBinding
import com.kopai.shinkansen.databinding.ActivityRegisterCompleteBinding
import com.kopai.shinkansen.view.authentication.login.LoginActivity

class RegisterCompleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}