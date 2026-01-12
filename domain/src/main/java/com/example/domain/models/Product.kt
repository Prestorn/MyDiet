package com.example.domain.models

data class Product(
    val id: Long,
    val name: String,
    val status: String,
    val categoryId: Long,
    val dietId: Long
)
