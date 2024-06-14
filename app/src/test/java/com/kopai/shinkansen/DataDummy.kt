package com.kopai.shinkansen

import com.kopai.shinkansen.data.remote.response.ProductItem

object DataDummy {
    fun generateDummyProducts(): List<ProductItem> {
        val newsList = ArrayList<ProductItem>()
        for (i in 0..10) {
            val news =
                ProductItem(
                    id = "id_$i",
                    name = "name_$i",
                    description = "description_$i",
                    photoUrl = "photoUrl_$i",
                    createdAt = "createdAt_$i",
                    lat = i.toDouble(),
                    lon = i.toDouble(),
                )
            newsList.add(news)
        }
        return newsList
    }
}
