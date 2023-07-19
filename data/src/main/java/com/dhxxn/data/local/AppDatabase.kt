package com.dhxxn.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun likeDao(): LikeDao

    companion object {
        const val DB_NAME = "Database_Dogterest"
    }
}