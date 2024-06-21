package com.kopai.shinkansen.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductsResponse(
	val listProducts: List<ProductItem>? = null
)

@Parcelize
@Entity(tableName = "products")
data class ProductItem(
	@PrimaryKey
	@field:SerializedName("product_id")
	val productId: Int? = null,
	@field:SerializedName("blend_id")
	val blendId: Int? = null,
	@field:SerializedName("product_name")
	val productName: String? = null,
	@field:SerializedName("quantity")
	val quantity: Int? = null,
	@field:SerializedName("bean")
	val bean: Boolean? = null,
	@field:SerializedName("size")
	val size: Int? = null,
	@field:SerializedName("price")
	val price: Int? = null,
	@field:SerializedName("grinded")
	val grinded: String? = null,
	@field:SerializedName("status")
	val status: String? = null,
	@field:SerializedName("description")
	val description: String? = null,
	@field:SerializedName("photo")
	val photo: String? = null
) : Parcelable

