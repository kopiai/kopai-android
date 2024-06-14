package com.kopai.shinkansen.view.authentication.register

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.ActivityRegisterBinding
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {
    @Inject lateinit var viewModel: RegisterViewModel

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
//        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener { registerButton ->
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()

            viewModel.register(name, email, password).observe(this) {
                when (it) {
                    is ResultState.Error -> {
                        binding.btnRegister.visibility = View.GONE
                        registerButton.isEnabled = true
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    }

                    ResultState.Loading -> {
                        binding.pBar.visibility = View.VISIBLE
                        registerButton.isEnabled = false
                    }

                    is ResultState.Success -> {
                        binding.pBar.visibility = View.GONE
                        registerButton.isEnabled = true
                        handleSuccessRegister(email)
                    }
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun handleSuccessRegister(email: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Yeah!")
            setMessage("Akun dengan $email berhasil dibuat.")
            setPositiveButton("Lanjut") { _, _ ->
                finish()
            }
            create()
            show()
        }
    }

//    private fun playAnimation() {
//        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }.start()
//
//        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
//        val nameTextView =
//            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
//        val nameEditTextLayout =
//            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
//        val emailTextView =
//            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
//        val emailEditTextLayout =
//            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
//        val passwordTextView =
//            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
//        val passwordEditTextLayout =
//            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
//        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(100)
//
//        AnimatorSet().apply {
//            playSequentially(
//                title,
//                nameTextView,
//                nameEditTextLayout,
//                emailTextView,
//                emailEditTextLayout,
//                passwordTextView,
//                passwordEditTextLayout,
//                signup,
//            )
//            startDelay = 100
//        }.start()
//    }
}
