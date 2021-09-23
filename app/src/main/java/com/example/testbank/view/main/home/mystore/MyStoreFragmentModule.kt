package com.example.testbank.view.main.home.mystore

import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.repository.local.model.search.SearchModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltMyStoreFragment

@Module
@InstallIn(FragmentComponent::class)
object MyStoreFragmentModule {
    @HiltMyStoreFragment
    @Provides
    fun provideTypeListAdapter(): BaseTypeListAdapter<SearchModel> =
        BaseTypeListAdapter(R.layout.item_mystore)

    @Module
    @InstallIn(FragmentComponent::class)
    interface BindModule {
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object MyStoreViewModelModule {
}