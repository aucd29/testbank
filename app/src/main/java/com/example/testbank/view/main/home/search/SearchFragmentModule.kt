package com.example.testbank.view.main.home.search

import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypePagedListAdapter
import com.example.testbank.repository.local.model.search.SearchModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltSearchFragment

@Module
@InstallIn(FragmentComponent::class)
object SearchFragmentModule {
    @HiltSearchFragment
    @Provides
    fun providePagedListAdapter(): BaseTypePagedListAdapter<SearchModel> =
        BaseTypePagedListAdapter(R.layout.item_search)
            
    @Module
    @InstallIn(FragmentComponent::class)
    interface BindModule {
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object SearchViewModelModule {
}