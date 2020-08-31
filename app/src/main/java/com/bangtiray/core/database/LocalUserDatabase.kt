package com.bangtiray.core.database

import android.content.Context
import androidx.room.*
import com.bangtiray.core.database.dao.LocalUserDao
import com.bangtiray.core.database.entity.LocalUser

@Database(entities = [LocalUser::class], version = 1)
abstract class LocalUserDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao
    companion object {
        @Volatile
        private var instance: LocalUserDatabase? = null

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, LocalUserDatabase::class.java, "local_user_db").build()

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

    }
}