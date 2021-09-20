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
import com.example.testbank.repository.HiltRepository
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.RepositoryManager
import com.example.testbank.repository.dummy.DummyRepository
import com.example.testbank.repository.dummy.HiltDummy
import com.example.testbank.repository.remote.dto.HiltRemote
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
        // 레파지토리
        //
        ////////////////////////////////////////////////////////////////////////////////////

        @HiltRepository
        @Binds
        fun bindRepositoryInterface(manager: RepositoryManager): RepositoryInterface

        @HiltDummy
        @Binds
        fun bindDummyRepository(dummyRepository: DummyRepository): RepositoryInterface

        @HiltRemote
        @Binds
        fun bindRemoteRepository(remoteRepository: RemoteRepository): RepositoryInterface
    }
}