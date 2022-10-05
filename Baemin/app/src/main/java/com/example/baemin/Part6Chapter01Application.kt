package com.example.baemin

import android.app.Application
import android.content.Context
import com.example.baemin.di.appModule
import org.koin.core.context.startKoin

class Part6Chapter01Application: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}