package com.example.domain.repositories

import com.example.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAll(dietId: Long, categoryId: Long): Flow<List<Product>>
    suspend fun getAllLikeName(dietId: Long, name: String): List<Product>
    suspend fun create(product: Product)
    suspend fun update(product: Product)
    suspend fun delete(product: Product)
}