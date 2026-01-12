package com.example.domain.usecases.category

import com.example.domain.models.Category
import com.example.domain.repositories.CategoryRepository

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: Category) = categoryRepository.create(category)
}