package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.repositories.ProductRepository

class CreateProductUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product) = productRepository.create(product)
}