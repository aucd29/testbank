package com.example.testbank.di.module.libs

import com.example.testbank.BuildConfig
import com.example.testbank.base.webview.CookieProxyInterface
import com.example.testbank.base.webview.MyCookieProxy
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

interface AuthorizationInterceptor : Interceptor

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
    fun provideAuthorizationInterceptor() = object : AuthorizationInterceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(
                chain.request().newBuilder()
                    .addHeader(AUTHORIZATION, "$KAKAO_AK $KAKAO_REST_AUTH")
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        logInterceptor: HttpLoggingInterceptor,
        cookieProxy: CookieProxyInterface,
        authorizationInterceptor: AuthorizationInterceptor,
    ) : OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .cookieJar(cookieProxy)
            .retryOnConnectionFailure(true)     // HTTP FAILED: java.io.IOException: unexpected end of stream on
            .addInterceptor(authorizationInterceptor)

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

    const val KAKAO_AK          = "KakaoAK"
    const val AUTHORIZATION     = "Authorization"
    const val KAKAO_REST_AUTH   = "e302331ef568c1a4af2053c77eef1b89"
}