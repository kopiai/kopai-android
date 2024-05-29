package com.kopai.shinkansen.data.pref

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)