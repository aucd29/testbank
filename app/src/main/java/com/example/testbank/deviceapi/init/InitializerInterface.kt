package com.example.testbank.base

import android.app.Application
import android.webkit.WebView
import com.example.testbank.BuildConfig
import com.example.testbank.deviceapi.system.NetworkStatusInterface
import io.reactivex.disposables.CompositeDisposable
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
) : InitializerInterface {
    override fun init() {
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
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