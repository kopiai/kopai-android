package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

typealias NewsResponse = PagingResponse<NewsResponseItem>

class NewsResponseItem(
    @field:SerializedName("date")
    val date: String,
    @field:SerializedName("newsID")
    val newsID: Int,
    @field:SerializedName("newsAuthor")
    val newsAuthor: String,
    @field:SerializedName("newsTitle")
    val newsTitle: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("picture")
    val picture: Any,
    @field:SerializedName("content")
    val content: String,
)