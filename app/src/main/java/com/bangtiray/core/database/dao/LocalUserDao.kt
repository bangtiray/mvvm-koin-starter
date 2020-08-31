package com.bangtiray.core.database.dao

import androidx.room.*
import com.bangtiray.core.database.entity.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalUserDao {
    @Query("select * from local_user order by first_name")
    fun localUsers(): Flow<MutableList<LocalUser>>

    @Query("select * from local_user where id = :id")
    fun localUser(id: String?) : LocalUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(localUser: LocalUser)

    @Update
    suspend fun update(localUser: LocalUser)

    @Delete
    suspend fun delete(localUser: LocalUser)
}