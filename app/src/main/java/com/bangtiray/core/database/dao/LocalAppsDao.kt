package com.bangtiray.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangtiray.core.database.entity.LocalApps
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalAppsDao {
    @Query("SELECT * FROM local_apps")
    fun loadApps(): Flow<List<LocalApps>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(localApps: List<LocalApps>)
}