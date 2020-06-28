package com.ankit.crunch.KoinModule

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Appkoin: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Appkoin)
            modules(viewModelModule)
            modules(retrofitServiceModule)
            modules(netWork)
            modules(databaseModule)
            modules(repositoryModule)
        }
    }}