package com.example.data.storage.dao

import androidx.room.*
import com.example.data.storage.entities.ProductInDiet
import com.example.data.storage.models.ProductWithStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductInDietDao {

    @Query("SELECT products.id, products.name, statuses.name AS status FROM product_in_diet " +
            "INNER JOIN products ON product_in_diet.productId = products.id " +
            "INNER JOIN statuses ON product_in_diet.statusId = statuses.id " +
            "WHERE dietId = :dietId AND categoryId = :categoryId")
    fun getAllByDietAndCategory(dietId: Long, categoryId: Long): Flow<List<ProductWithStatus>>

    @Query("SELECT products.id, products.name, statuses.name AS status FROM product_in_diet " +
            "INNER JOIN products ON product_in_diet.productId = products.id " +
            "INNER JOIN statuses ON product_in_diet.statusId = statuses.id " +
            "WHERE products.name LIKE '%' || :name || '%' AND dietId = :dietId")
    suspend fun getLikeName(name: String, dietId: Long): List<ProductWithStatus>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productInDiet: ProductInDiet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg productInDiet: ProductInDiet)

    @Update
    suspend fun update(productInDiet: ProductInDiet)

    @Delete
    suspend fun delete(productInDiet: ProductInDiet)

    @Query("DELETE FROM product_in_diet WHERE dietId = :dietId AND productId = :productId")
    suspend fun deleteByDietAndProduct(dietId: Long, productId: Long)

    @Query("DELETE FROM product_in_diet WHERE dietId = :dietId")
    suspend fun deleteByDietId(dietId: Long)
}