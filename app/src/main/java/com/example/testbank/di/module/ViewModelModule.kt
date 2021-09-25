package com.example.testbank.di.module

import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun providePagedListConfig(): PagedList.Config =
        PagedList.Config.Builder()
            .setPageSize(LOAD_MORE_SIZE)
            .setInitialLoadSizeHint(LOAD_FIRST_SIZE)
            .setPrefetchDistance(LOAD_FIRST_SIZE)
            .setEnablePlaceholders(true)
            .setMaxSize(PagedList.Config.MAX_SIZE_UNBOUNDED)
            .build()

    @Module
    @InstallIn(ViewModelComponent::class)
    interface BindModule {

    }

    const val LOAD_MORE_SIZE = 60
    const val LOAD_FIRST_SIZE = 60
}