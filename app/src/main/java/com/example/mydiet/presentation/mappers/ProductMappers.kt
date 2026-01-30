package com.example.mydiet.presentation.mappers

import com.example.domain.models.Product as DomainProduct
import com.example.mydiet.presentation.models.Product as PresentationProduct

fun DomainProduct.toPresentation() = PresentationProduct(
    id = id,
    name = name,
    status = status,
    categoryId = categoryId,
    dietId = dietId
)