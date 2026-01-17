package com.example.data.repositoryimpl

import com.example.data.storage.dao.DietDao
import com.example.data.storage.dao.ProductDao
import com.example.data.storage.dao.ProductInDietDao
import com.example.data.storage.entities.ProductInDiet
import com.example.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import com.example.data.storage.entities.Product as DataProduct
import com.example.data.storage.models.ProductWithStatus as DataProductWithStatus
import com.example.domain.models.Product as DomainProduct

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val productInDietDao: ProductInDietDao,
    private val dietDao: DietDao
) : ProductRepository {
    override fun getAll(dietId: Long, categoryId: Long): Flow<List<DomainProduct>> {
        return productInDietDao.getAllByDietAndCategory(dietId, categoryId).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getAllLikeName(dietId: Long, name: String): List<DomainProduct> {
        return productInDietDao.getLikeName(
            name = name,
            dietId = dietId
        ).map{it.toDomain()}
    }

    override suspend fun create(product: DomainProduct) {
        val diets = dietDao.getAll().first()
        productDao.insert(product.toData())
        diets.forEach { diet ->
            if (diet.id == product.dietId) {
                productInDietDao.insert(product.toProductInDiet())
            } else {
                productInDietDao.insert(ProductInDiet(
                    dietId = diet.id,
                    productId = product.id,
                    statusId = 2
                ))
            }
        }
    }

    override suspend fun update(product: DomainProduct) {
        productDao.update(product.toData())
    }

    override suspend fun delete(product: DomainProduct) {
        productDao.delete(product.toData())
    }

    private fun DomainProduct.toData(): DataProduct {
        return DataProduct(
            id = this.id,
            name = this.name,
            categoryId = this.categoryId
        )
    }

    private fun DataProductWithStatus.toDomain(): DomainProduct {
        return DomainProduct(
            id = this.id,
            name =  this.name,
            status = this.status,
            categoryId = 0,
            dietId = 0
        )
    }

    private fun DomainProduct.toProductInDiet(): ProductInDiet {
        return ProductInDiet(
            dietId = this.dietId,
            productId = this.id,
            statusId = when(this.status) {
                "Разрешено" -> 1
                "Под вопросом" -> 2
                "Запрещено" -> 3
                else -> 0
            }
        )
    }
}