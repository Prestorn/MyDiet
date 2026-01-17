package com.example.domain.usecases.diet

import com.example.domain.models.Diet
import com.example.domain.repositories.DietRepository
import kotlinx.coroutines.flow.Flow

class GetAllDietsUseCase(private val dietRepository: DietRepository) {
    operator fun invoke(): Flow<List<Diet>> = dietRepository.getAll()
}