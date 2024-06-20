package com.kopai.shinkansen.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kopai.shinkansen.data.remote.response.ProductsItem

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(quote: List<ProductsItem?>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): PagingSource<Int, ProductsItem>

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}
