package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductResponse(
    @field:SerializedName("listStory")
    val listStory: List<ProductItem>,
    @field:SerializedName("error")
    val error: Boolean? = null,
)

@Parcelize
@Entity(tableName = "stories")
data class ProductItem(
    @PrimaryKey
    @field:SerializedName("id")
    val id: String = "id_",
    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,
    @field:SerializedName("createdAt")
    val createdAt: String? = null,
) : Parcelable
