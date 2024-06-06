package com.kopai.shinkansen.data.local.pref

data class UserPrefModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false,
)
