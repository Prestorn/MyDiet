package com.example.data.storage.dao

import androidx.room.*
import com.example.data.storage.entities.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface StatusDao {

    @Query("SELECT * FROM statuses")
    fun getAll(): Flow<List<Status>>

    @Query("SELECT * FROM statuses WHERE id = :id")
    suspend fun getById(id: Long): Status?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(status: Status): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg statuses: Status)

    @Update
    suspend fun update(status: Status)

    @Delete
    suspend fun delete(status: Status)
}