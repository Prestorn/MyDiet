package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.repositories.ProductRepository

class GetProductsLikeNameUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(dietId: Long, name: String): List<Product> = productRepository.getAllLikeName(dietId, name)
}