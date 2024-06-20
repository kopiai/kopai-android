package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class StoriesResponse(
    @field:SerializedName("listStory")
    val listStories: List<StoryItem>,
    @field:SerializedName("error")
    val error: Boolean? = null,
    @field:SerializedName("messsage")
    val message: String? = null,
)

@Parcelize
@Entity(tableName = "stories")
data class StoryItem(
    @PrimaryKey
    @field:SerializedName("id")
    val id: String = "id_",
    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,
    @field:SerializedName("createdAt")
    val createdAt: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("lat")
    val lat: Double? = null,
    @field:SerializedName("lon")
    val lon: Double? = null,
) : Parcelable
