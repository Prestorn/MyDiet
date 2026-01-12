package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
    operator fun invoke(): Flow<List<Product>> = productRepository.getAll(0, 0)
}