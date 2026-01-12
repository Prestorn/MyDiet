package com.example.domain.models


data class ProductInDiet(
    val dietId: Long,
    val productId: Long,
    val statusId: Long? = null
)
