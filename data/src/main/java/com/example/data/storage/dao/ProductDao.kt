package com.example.data.storage.dao

import androidx.room.*
import com.example.data.storage.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: Long): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteById(id: Long)
}