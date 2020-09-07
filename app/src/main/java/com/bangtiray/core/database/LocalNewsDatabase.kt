package com.bangtiray.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangtiray.core.database.dao.LocalNewsDao
import com.bangtiray.core.database.entity.LocalNews


@Database(entities = [LocalNews::class], version = 1)
abstract class LocalNewsDatabase : RoomDatabase() {
    abstract fun localNewsDao(): LocalNewsDao

    companion object {
        private var instance: LocalNewsDatabase? = null

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, LocalNewsDatabase::class.java, "local_news_db").build()

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }
}