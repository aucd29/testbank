package com.example.testbank

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.testbank.base.InitializerInterface
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApp : Application()  {
    @Inject
    lateinit var initializer: InitializerInterface

    override fun onCreate() {
        super.onCreate()

        initializer.init()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTerminate() {
        initializer.terminate()

        super.onTerminate()
    }
}