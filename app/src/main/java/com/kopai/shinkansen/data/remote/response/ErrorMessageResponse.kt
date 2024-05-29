package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorMessageResponse(
    @field:SerializedName("error")
    val error: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
)
