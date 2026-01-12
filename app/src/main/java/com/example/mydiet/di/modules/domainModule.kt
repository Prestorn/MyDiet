package com.example.mydiet.di.modules

import com.example.data.repositoryimpl.CategoryRepositoryImpl
import com.example.data.repositoryimpl.DietRepositoryImpl
import com.example.data.repositoryimpl.ProductRepositoryImpl
import com.example.domain.repositories.CategoryRepository
import com.example.domain.repositories.DietRepository
import com.example.domain.repositories.ProductRepository
import com.example.domain.usecases.category.CreateCategoryUseCase
import com.example.domain.usecases.category.DeleteCategoryUseCase
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.category.RenameCategoryUseCase
import com.example.domain.usecases.diet.CreateDietUseCase
import com.example.domain.usecases.diet.DeleteDietUseCase
import com.example.domain.usecases.diet.GetAllDietsUseCase
import com.example.domain.usecases.diet.RenameDietUseCase
import com.example.domain.usecases.product.CreateProductUseCase
import com.example.domain.usecases.product.DeleteProductUseCase
import com.example.domain.usecases.product.GetAllProductsUseCase
import com.example.domain.usecases.product.GetProductsLikeNameUseCase
import com.example.domain.usecases.product.RenameProductUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    single<CategoryRepository>{ CategoryRepositoryImpl(get()) }
    single<DietRepository>{ DietRepositoryImpl(get()) }
    single<ProductRepository>{ ProductRepositoryImpl(get(), get(), get()) }
    factoryOf(::CreateCategoryUseCase)
    factoryOf(::DeleteCategoryUseCase)
    factoryOf(::GetAllCategoriesUseCase)
    factoryOf(::RenameCategoryUseCase)
    factoryOf(::CreateDietUseCase)
    factoryOf(::DeleteDietUseCase)
    factoryOf(::GetAllDietsUseCase)
    factoryOf(::RenameDietUseCase)
    factoryOf(::CreateProductUseCase)
    factoryOf(::DeleteProductUseCase)
    factoryOf(::GetAllProductsUseCase)
    factoryOf(::GetProductsLikeNameUseCase)
    factoryOf(::RenameProductUseCase)

}