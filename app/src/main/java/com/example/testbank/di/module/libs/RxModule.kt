package com.example.testbank.di.module.libs

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RxModule {
    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable =
        CompositeDisposable()
}