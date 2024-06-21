package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

typealias ProductsResponse = PagingResponse<ProductItem>

@Entity(tableName = "products")
@Parcelize
data class ProductItem(
    @PrimaryKey
    @field:SerializedName("product_id") val productId: Int,
    @field:SerializedName("product_name") val productName: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("photo") val photo: String?,
    @field:SerializedName("blend_id") val blendId: Int?,
    @field:SerializedName("quantity") val quantity: Int,
    @field:SerializedName("size") val size: Int,
    @field:SerializedName("price") val price: Int,
    @field:SerializedName("grinded") val grinded: Boolean?,
    @field:SerializedName("bean") val bean: Boolean?,
    @field:SerializedName("status") val status: Boolean?,
) : Parcelable