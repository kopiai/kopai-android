package com.kopai.shinkansen.data.local.entity

import androidx.room.PrimaryKey

data class Product(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?,
)
