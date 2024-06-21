package com.kopai.shinkansen

import com.kopai.shinkansen.data.remote.response.ProductItem

object DataDummy {
    fun generateDummyProducts(): List<ProductItem> {
        val productItems = ArrayList<ProductItem>()
        for (i in 0..10) {
            val productItem =
                ProductItem(
                    productId = i,
                    productName = "Product $i",
                    description = "Description $i",
                    photo = "https://www.google.com",
                    quantity = i,
                    size = i,
                    price = i * 10000,
                    blendId = i,
                    grinded = i % 2 == 0,
                    bean = i % 2 == 0,
                    status = i % 2 == 0,
                )
            productItems.add(productItem)
        }
        return productItems
    }
}
