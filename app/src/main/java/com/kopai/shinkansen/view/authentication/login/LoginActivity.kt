package com.kopai.shinkansen.view.authentication.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.ActivityLoginBinding
import com.kopai.shinkansen.util.Constant
import com.kopai.shinkansen.util.Helper
import com.kopai.shinkansen.view.authentication.preferences.PreferencesViewModel
import com.kopai.shinkansen.view.authentication.recovery.RecoveryAccountActivity
import com.kopai.shinkansen.view.authentication.register.RegisterActivity
import com.kopai.shinkansen.view.main.MainActivity
import com.kopai.shinkansen.view.shared.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    private val tokenViewModel: TokenViewModel by viewModels()

    private val preferencesViewModel: PreferencesViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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
        binding.btnLogin.setOnClickListener { loginButton ->
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()

            when {
                email.isEmpty() or password.isEmpty() -> {
                    Helper.showDialogInfo(
                        this,
                        getString(R.string.UI_validation_empty_email_password),
                    )
                }

                !email.matches(Constant.emailPattern) -> {
                    Helper.showDialogInfo(
                        this,
                        getString(R.string.UI_validation_invalid_email),
                    )
                }

                password.length <= 6 -> {
                    Helper.showDialogInfo(
                        this,
                        getString(R.string.UI_validation_password_rules),
                    )
                }

                else -> {
                    loginViewModel.login(email, password).observe(this) {
                        when (it) {
                            is ResultState.Error -> {
                                binding.pBar.visibility = View.GONE
                                loginButton.isEnabled = true
                                Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                            }

                            ResultState.Loading -> {
                                binding.pBar.visibility = View.VISIBLE
                                loginButton.isEnabled = false
                            }

                            is ResultState.Success -> {
                                binding.pBar.visibility = View.GONE
                                loginButton.isEnabled = true
                                handleSuccessLogin(
                                    it.data.user?.userId.toString() ?: "",
                                    it.data.user?.name ?: "",
                                    email,
                                    it.data.token ?: "",
                                )
                            }
                        }
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, RecoveryAccountActivity::class.java))
        }
    }

    private fun handleSuccessLogin(
        userId: String,
        name: String,
        email: String,
        token: String,
    ) {
        tokenViewModel.saveToken(userId, name, email, token)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()

        // Setup if Api is ready
//        preferencesViewModel.getPreferences(userId).observe(this) {preferencesResponse ->
//                when (preferencesResponse) {
//                    is ResultState.Error -> {
//                        val intent = Intent(this, PreferencesOneActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                        startActivity(intent)
//                        finish()
//                    }
//                    ResultState.Loading -> {
//                        Log.d("MainActivity", "Loading fetch banner")
//                    }
//                    is ResultState.Success -> {
//                        val intent = Intent(this, MainActivity::class.java)
//                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                        startActivity(intent)
//                        finish()
//                    }
//                }
//            }
    }

//    private fun playAnimation() {
//        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
//        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }.start()
//
//        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
//        val message =
//            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
//        val emailTextView =
//            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
//        val emailEditTextLayout =
//            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
//        val passwordTextView =
//            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
//        val passwordEditTextLayout =
//            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
//        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)
//
//        AnimatorSet().apply {
//            playSequentially(
//                title,
//                message,
//                emailTextView,
//                emailEditTextLayout,
//                passwordTextView,
//                passwordEditTextLayout,
//                login,
//            )
//            startDelay = 100
//        }.start()
//    }
}
