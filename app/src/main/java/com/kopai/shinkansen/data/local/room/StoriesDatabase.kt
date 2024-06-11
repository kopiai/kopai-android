package com.kopai.shinkansen.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kopai.shinkansen.data.local.entity.RemoteKeysEntity
import com.kopai.shinkansen.data.remote.response.StoryItem

@Database(
    entities = [StoryItem::class, RemoteKeysEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class StoriesDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao

    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Suppress("ktlint:standard:property-naming")
        @Volatile
        private var INSTANCE: StoriesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StoriesDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StoriesDatabase::class.java,
                    "stories_database",
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
