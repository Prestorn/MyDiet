package com.example.mydiet.presentation.ui.diet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.product.GetProductsLikeNameUseCase
import com.example.mydiet.presentation.mappers.toPresentation
import com.example.mydiet.presentation.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DietViewModel(
    private val getProductsLikeNameUseCase: GetProductsLikeNameUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products : StateFlow<List<Product>> = _products

    fun getProductsLikeName(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                _products.value = getProductsLikeNameUseCase(name).map { it.toPresentation()}
            }
        } else {
            _products.value = listOf()
        }
    }
}