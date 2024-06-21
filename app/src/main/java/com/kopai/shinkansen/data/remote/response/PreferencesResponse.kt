package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// Dummy
data class PreferencesResponse(
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("preference")
    val preference: List<Preferenceitem>,
)

@Parcelize
data class Preferenceitem(
    @field:SerializedName("preference_id")
    val preferenceId: Int? = null,
    @field:SerializedName("user_id")
    val userId: Int? = null,
    @field:SerializedName("effect")
    val effect: String? = null,
    @field:SerializedName("healthIssue")
    val healthIssue: String? = null,
    @field:SerializedName("preferredAroma")
    val preferredAroma: String? = null,
    @field:SerializedName("preferredTaste")
    val preferredTaste: String? = null,
) : Parcelable

