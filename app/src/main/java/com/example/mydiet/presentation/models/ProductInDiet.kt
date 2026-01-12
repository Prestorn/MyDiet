package com.example.mydiet.presentation.models


data class ProductInDiet(
    val dietId: Long,
    val productId: Long,
    val statusId: Long? = null
)
