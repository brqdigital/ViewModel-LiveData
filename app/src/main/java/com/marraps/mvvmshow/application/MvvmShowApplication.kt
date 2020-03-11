package com.marraps.mvvmshow.application

import android.app.Application
import com.marraps.mvvmshow.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MvvmShowApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MvvmShowApplication)
            androidLogger()
            modules(appModule)
        }
    }
}