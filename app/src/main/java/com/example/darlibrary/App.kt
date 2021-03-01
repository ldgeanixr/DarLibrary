package com.example.darlibrary

import android.app.Application
import com.example.darlibrary.di.dbModule
import com.example.darlibrary.di.mainModule
import com.example.darlibrary.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(
                dbModule,
                mainModule,
                networkModule
            )
        }
    }
}