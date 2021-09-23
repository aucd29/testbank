package com.example.testbank.base

import android.app.Application
import android.webkit.WebView
import com.example.testbank.BuildConfig
import com.example.testbank.deviceapi.system.NetworkStatusInterface
import com.example.testbank.di.module.libs.TimberDebugTree
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface InitializerInterface {
    fun init()
    fun terminate()
}

@Singleton
class MyInitializer @Inject constructor(
    private val app: Application,
    private val networkStatus: NetworkStatusInterface,
    private val disposable: CompositeDisposable,
    private val timberDebugTree: dagger.Lazy<TimberDebugTree>,
) : InitializerInterface {
    override fun init() {
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
            Timber.plant(timberDebugTree.get())
        }

        networkStatus.registerNetworkStatus()
        preloadData()
    }

    private fun preloadData() {

    }

    override fun terminate() {
        networkStatus.unregisterNetworkStatus()
        disposable.dispose()
    }
}