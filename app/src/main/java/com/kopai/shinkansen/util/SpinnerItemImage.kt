package com.kopai.shinkansen.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpinnerItemImage(
    val imageResId: String,
    val text: String
) : Parcelable
