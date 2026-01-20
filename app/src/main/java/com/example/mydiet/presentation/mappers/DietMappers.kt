package com.example.mydiet.presentation.mappers

import com.example.domain.models.Diet as DietDomain
import com.example.mydiet.presentation.models.Diet as DietPresentation

fun DietPresentation.toDomain() = DietDomain(id = id, name = name)
fun DietDomain.toPresentation() = DietPresentation(id = id, name = name)