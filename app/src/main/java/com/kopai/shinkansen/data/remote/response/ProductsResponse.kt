package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductsResponse(
	val listProducts: List<ProductsItem?>? = null
)

@Parcelize
@Entity(tableName = "products")
data class ProductsItem(
	@field:SerializedName("product_id")
	val productId: Int? = null,
	val blendId: Int? = null,
	val productName: String? = null,
	val quantity: Int? = null,
	val bean: Boolean? = null,
	val size: Int? = null,
	val price: Int? = null,
	val grinded: String? = null,
	val status: String? = null,
	val description: String? = null,
	val photo: String? = null
) : Parcelable

