package com.example.domain.usecases.category

import com.example.domain.models.Category
import com.example.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> = categoryRepository.getAll()
}