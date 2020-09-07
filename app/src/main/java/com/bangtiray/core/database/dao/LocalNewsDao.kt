package com.bangtiray.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangtiray.core.database.entity.LocalNews

import kotlinx.coroutines.flow.Flow


@Dao
interface LocalNewsDao {
    @Query("SELECT * FROM local_news order by publish_datetime desc")
    fun loadNews(): Flow<List<LocalNews>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(localNews: List<LocalNews>)
}