package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

typealias NewsResponse = PagingResponse<NewsResponseItem>

@Parcelize
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
    val picture: String,
    @field:SerializedName("content")
    val content: String,
) : Parcelable