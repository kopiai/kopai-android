package com.kopai.shinkansen.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kopai.shinkansen.data.local.entity.RemoteKeysEntity
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.data.remote.response.ProductsItem

@Database(
    entities = [ProductsItem::class, RemoteKeysEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Suppress("ktlint:standard:property-naming")
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ProductsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_database",
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
