package com.kopai.shinkansen.view.authentication.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityRegisterBinding
import com.kopai.shinkansen.databinding.ActivityRegisterCompleteBinding

class RegisterCompleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}