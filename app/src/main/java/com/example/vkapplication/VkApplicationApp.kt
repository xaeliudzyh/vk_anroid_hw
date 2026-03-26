package com.example.vkapplication

import android.app.Application
import com.example.vkapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VkApplicationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VkApplicationApp)
            modules(appModule)
        }
    }
}
