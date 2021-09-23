package com.example.testbank.di.module

import android.webkit.CookieManager
 import com.example.testbank.base.InitializerInterface
 import com.example.testbank.base.MyInitializer
 import com.example.testbank.base.webview.CookieInterface
 import com.example.testbank.base.webview.MyCookieManager
 import com.example.testbank.deviceapi.system.MyNetworkStatus
 import com.example.testbank.deviceapi.system.NetworkStatusInterface
 import com.example.testbank.deviceapi.vibrator.MyVibrator
 import com.example.testbank.deviceapi.vibrator.VibratorInterface
import com.example.testbank.repository.HiltRepositoryManager
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.RepositoryManager
import com.example.testbank.repository.dummy.DummyRepository
import com.example.testbank.repository.dummy.HiltDummyRepository
import com.example.testbank.repository.remote.dto.HiltRemoteRepository
import com.example.testbank.repository.remote.dto.RemoteRepository
import dagger.Binds
 import dagger.Module
 import dagger.Provides
 import dagger.hilt.InstallIn
 import dagger.hilt.components.SingletonComponent
 import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Provides
    @Singleton
    fun provideCookieManager(): CookieManager =
        CookieManager.getInstance()

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {
        @Binds
        fun bindInitializerInterface(initializer: MyInitializer): InitializerInterface

        @Binds
        fun bindVibratorInterface(vibrate: MyVibrator): VibratorInterface

        @Binds
        fun bindNetworkStatusInterface(networkStatus: MyNetworkStatus): NetworkStatusInterface

        @Binds
        fun bindCookieInterface(cookieManager: MyCookieManager): CookieInterface

        ////////////////////////////////////////////////////////////////////////////////////
        //
        // REPOSITORY
        //
        ////////////////////////////////////////////////////////////////////////////////////

        @HiltRepositoryManager
        @Binds
        fun bindRepositoryInterface(manager: RepositoryManager): RepositoryInterface

        @HiltDummyRepository
        @Binds
        fun bindDummyRepository(dummyRepository: DummyRepository): RepositoryInterface

        @HiltRemoteRepository
        @Binds
        fun bindRemoteRepository(remoteRepository: RemoteRepository): RepositoryInterface
    }
}