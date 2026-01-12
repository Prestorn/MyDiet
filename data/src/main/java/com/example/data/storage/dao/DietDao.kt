package com.example.data.storage.dao

import androidx.room.*
import com.example.data.storage.entities.Diet
import kotlinx.coroutines.flow.Flow

@Dao
interface DietDao {

    @Query("SELECT * FROM diets")
    fun getAll(): Flow<Diet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diet: Diet): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg diets: Diet)

    @Update
    suspend fun update(diet: Diet)

    @Delete
    suspend fun delete(diet: Diet)

    @Query("DELETE FROM diets WHERE id = :id")
    suspend fun deleteById(id: Long)
}