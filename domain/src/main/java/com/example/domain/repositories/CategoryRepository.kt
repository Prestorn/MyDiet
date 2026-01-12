package com.example.domain.repositories

import com.example.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAll(): Flow<List<Category>>
    suspend fun create(category: Category)
    suspend fun update(category: Category)
    suspend fun delete(category: Category)
}