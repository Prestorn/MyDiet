package com.example.mydiet.presentation.ui.dietslist

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.diet.CreateDietUseCase
import com.example.domain.usecases.diet.GetAllDietsUseCase
import com.example.domain.usecases.diet.RenameDietUseCase

class DietsListViewModel(
    private val getAllDietsUseCase: GetAllDietsUseCase,
    private val createDietUseCase: CreateDietUseCase,
    private val deleteDietUseCase: CreateDietUseCase,
    private val renameDietUseCase: RenameDietUseCase
) : ViewModel() {
    val diets = getAllDietsUseCase()
}