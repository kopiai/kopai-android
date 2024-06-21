package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

data class PagingResponse<T>(
    @field:SerializedName("data")
    val data: List<T>,
    @field:SerializedName("meta")
    val meta: PagingMeta,
)

data class PagingMeta(
    @field:SerializedName("totalNews")
    val totalNews: Int,
    @field:SerializedName("totalPages")
    val totalPages: Int,
    @field:SerializedName("pageSize")
    val pageSize: Int,
    @field:SerializedName("currentPage")
    val currentPage: Int,
)