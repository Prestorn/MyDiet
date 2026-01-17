package com.example.domain.repositories

import com.example.domain.models.Diet
import kotlinx.coroutines.flow.Flow

interface DietRepository {
    fun getAll(): Flow<List<Diet>>
    suspend fun create(diet: Diet)
    suspend fun update(diet: Diet)
    suspend fun delete(diet: Diet)
}