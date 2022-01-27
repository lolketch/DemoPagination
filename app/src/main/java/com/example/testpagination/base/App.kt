package com.example.testpagination.base

import android.app.Application
import com.example.testpagination.di.AppComponent
import com.example.testpagination.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}