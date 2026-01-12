package com.example.domain.usecases.diet

import com.example.domain.models.Diet
import com.example.domain.repositories.DietRepository

class RenameDietUseCase(private val dietRepository: DietRepository) {
    suspend operator fun invoke(diet: Diet) = dietRepository.update(diet)
}