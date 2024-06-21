package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("user")
    val user: LoginResult? = null,
    @field:SerializedName("token")
    val token: String? = null,
)

data class LoginResult(
    @field:SerializedName("user_id")
    val userId: Int? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("gender")
    val gender: String? = null,
    @field:SerializedName("birth")
    val birth: String? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("phone")
    val phone: Int? = null,
    @field:SerializedName("password")
    val password: String? = null,
    @field:SerializedName("address")
    val address: String? = null,
    @field:SerializedName("photo")
    val photo: String? = null,
)
