package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.repositories.ProductRepository

class DeleteProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product) = productRepository.delete(product)
}