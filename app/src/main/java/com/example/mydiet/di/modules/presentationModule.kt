package com.example.mydiet.di.modules

import com.example.mydiet.presentation.ui.dietslist.DietsListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::DietsListViewModel)
}