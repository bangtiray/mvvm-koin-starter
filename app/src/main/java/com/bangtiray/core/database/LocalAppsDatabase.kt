package com.bangtiray.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangtiray.core.database.dao.LocalAppsDao
import com.bangtiray.core.database.entity.LocalApps

@Database(entities = [LocalApps::class], version = 1)
abstract class LocalAppsDatabase : RoomDatabase() {
    abstract fun localAppsDao(): LocalAppsDao

    companion object {
        @Volatile
        private var instance: LocalAppsDatabase? = null
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, LocalAppsDatabase::class.java, "local_apps_db").build()

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

    }
}