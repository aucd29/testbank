package com.example.testbank.di.module.libs

import com.example.testbank.BuildConfig
import com.example.testbank.base.webview.CookieProxyInterface
import com.example.testbank.base.webview.MyCookieProxy
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkhttpModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        logInterceptor: HttpLoggingInterceptor,
        cookieProxy: CookieProxyInterface
    ) : OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .cookieJar(cookieProxy)

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(logInterceptor)
        }

        return builder.build()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {
        @Binds
        fun bindCookieProxyInterface(cookieProxy: MyCookieProxy): CookieProxyInterface
    }
}