package com.example.testbank.di.module.libs

import com.example.testbank.base.extension.moshiBuild
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRxJava2AdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createAsync()

    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory =
        MoshiConverterFactory.create(moshiBuild())

    @Provides
    fun provideRetrofitBuilder(
        okhttp: OkHttpClient,
        rxjavaAdapter: RxJava2CallAdapterFactory,
        moshiConverter: MoshiConverterFactory,
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addCallAdapterFactory(rxjavaAdapter)
            .addConverterFactory(moshiConverter)
            .client(okhttp)
}