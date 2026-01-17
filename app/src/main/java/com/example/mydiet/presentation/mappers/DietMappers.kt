package com.example.mydiet.presentation.mappers

import android.R.attr.description
import com.example.domain.models.Diet as DietDomain
import com.example.mydiet.presentation.models.Diet as DietPresentation

fun DietPresentation.toDomain() = DietDomain(name = name)
fun DietDomain.toPresentation() = DietPresentation(id = id, name = name)