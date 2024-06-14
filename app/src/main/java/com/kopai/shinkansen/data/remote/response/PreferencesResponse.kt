package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// Dummy
data class PreferencesResponse(
    @field:SerializedName("listStory")
    val listStory: List<Preferencesitem>,
    @field:SerializedName("error")
    val error: Boolean? = null,
)

@Parcelize
data class Preferencesitem(
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

