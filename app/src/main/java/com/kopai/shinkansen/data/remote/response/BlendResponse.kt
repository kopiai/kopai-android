package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

data class BlendResponse(
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("blend")
    val blend: BlendResult? = null,
)

data class BlendResult(
    @field:SerializedName("blend_id")
    val blendId: Int? = null,
    @field:SerializedName("coffee_id1")
    val coffeeIdOne: Int? = null,
    @field:SerializedName("coffee_id2")
    val coffeeIdTwo: Int? = null,
    @field:SerializedName("percentage")
    val percentage: Int? = null,
    @field:SerializedName("ukuran_gram")
    val totalWeight: Int? = null,
    @field:SerializedName("roast_id")
    val roastId: String? = null,
    @field:SerializedName("grind_id")
    val password: String? = null,
    @field:SerializedName("address")
    val address: String? = null,
    @field:SerializedName("photo")
    val photo: String? = null,
)
