package com.example.data.repositoryimpl

import com.example.data.storage.dao.DietDao
import com.example.domain.repositories.DietRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.data.storage.entities.Diet as DataDiet
import com.example.domain.models.Diet as DomainDiet

class DietRepositoryImpl(
    private val dietDao: DietDao
) : DietRepository {
    override fun getAll(): Flow<List<DomainDiet>> {
        return dietDao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun create(diet: DomainDiet) {
        dietDao.insert(diet.toData())
    }

    override suspend fun update(diet: DomainDiet) {
        dietDao.update(diet.toData())
    }

    override suspend fun delete(diet: DomainDiet) {
        dietDao.delete(diet.toData())
    }

    private fun DataDiet.toDomain(): DomainDiet {
        return DomainDiet(
            id = this.id,
            name = this.name
        )
    }

    private fun DomainDiet.toData(): DataDiet {
        return DataDiet(
            id = this.id,
            name = this.name
        )
    }

}