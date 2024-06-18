package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @field:SerializedName("userID")
    val userID: Int,
    @field:SerializedName("orderItems")
    val orderItems: List<OrderItemResponse>,
    @field:SerializedName("status")
    val status: String,
)

data class OrderItemResponse(
    @field:SerializedName("blend_id")
    val blendId: Int? = null,
    @field:SerializedName("quantity")
    val quantity: Int,
    @field:SerializedName("totalPrice")
    val totalPrice: Double,
    @field:SerializedName("product_id")
    val productId: Int,
    @field:SerializedName("order_id")
    val orderId: Int? = null,
)
