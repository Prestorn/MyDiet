package com.example.data.repositoryimpl

import com.example.data.storage.dao.CategoryDao
import com.example.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.data.storage.entities.Category as DataCategory
import com.example.domain.models.Category as DomainCategory

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override fun getAll(): Flow<List<DomainCategory>> {
        return categoryDao.getAll().map { dataCategories ->
            dataCategories.map { it.toDomain() }
        }
    }

    override suspend fun create(category: DomainCategory) {
        categoryDao.insert(category.toData())
    }

    override suspend fun update(category: DomainCategory) {
        categoryDao.update(category.toData())
    }

    override suspend fun delete(category: DomainCategory) {
        categoryDao.delete(category.toData())
    }

    private fun DataCategory.toDomain(): DomainCategory {
        return DomainCategory(
            id = this.id,
            name = this.name
        )
    }

    private fun DomainCategory.toData(): DataCategory {
        return DataCategory(
            id = this.id,
            name = this.name
        )
    }
}