package com.example.mydiet.di.modules

import org.koin.dsl.module
import com.example.data.storage.AppDb
import com.example.data.storage.dao.CategoryDao
import com.example.data.storage.dao.DietDao
import com.example.data.storage.dao.ProductDao
import com.example.data.storage.dao.ProductInDietDao
import com.example.data.storage.dao.StatusDao
import org.koin.core.module.dsl.singleOf

val dataModule = module {
    single<AppDb> { AppDb.getDatabase(get()) }
    single<DietDao> { AppDb.getDatabase(get()).dietDao() }
    single<CategoryDao> { AppDb.getDatabase(get()).categoryDao() }
    single<ProductDao> { AppDb.getDatabase(get()).productDao() }
    single<ProductInDietDao> { AppDb.getDatabase(get()).productInDietDao() }
    single<StatusDao> { AppDb.getDatabase(get()).statusDao() }
}
