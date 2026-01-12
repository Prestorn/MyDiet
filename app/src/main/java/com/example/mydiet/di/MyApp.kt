package com.example.mydiet.di

import android.app.Application
import com.example.mydiet.di.modules.dataModule
import com.example.mydiet.di.modules.domainModule
import com.example.mydiet.di.modules.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}