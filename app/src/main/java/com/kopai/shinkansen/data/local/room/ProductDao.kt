package com.kopai.shinkansen.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kopai.shinkansen.data.remote.response.ProductItem

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: List<ProductItem>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): PagingSource<Int, ProductItem>

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}
