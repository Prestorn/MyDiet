package com.example.mydiet.presentation.ui.dietslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.diet.CreateDietUseCase
import com.example.domain.usecases.diet.DeleteDietUseCase
import com.example.domain.usecases.diet.GetAllDietsUseCase
import com.example.domain.usecases.diet.RenameDietUseCase
import com.example.mydiet.presentation.mappers.toDomain
import com.example.mydiet.presentation.mappers.toPresentation
import com.example.mydiet.presentation.models.Diet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DietsListViewModel(
    getAllDietsUseCase: GetAllDietsUseCase,
    private val createDietUseCase: CreateDietUseCase,
    private val deleteDietUseCase: DeleteDietUseCase,
    private val renameDietUseCase: RenameDietUseCase
) : ViewModel() {
    val diets: Flow<List<Diet>> = getAllDietsUseCase().map { list -> list.map { it.toPresentation() } }

    fun createDiet() {
        viewModelScope.launch(Dispatchers.IO) {
            createDietUseCase(Diet(name = "Новая диета").toDomain())
        }
    }

    fun deleteDiet(diet: Diet) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDietUseCase(diet.toDomain())
        }
    }

    fun renameDiet(diet: Diet) {
        viewModelScope.launch(Dispatchers.IO) {
            renameDietUseCase(diet.toDomain())
        }
    }
}