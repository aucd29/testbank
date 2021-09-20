package com.example.testbank.view.main.more

import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.repository.local.model.more.*
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_BANNER
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_BAR
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_HEADER
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_MENU
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_SUBJECT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.Single
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltMoreFragment

@Module
@InstallIn(FragmentComponent::class)
object MoreFragmentModule {
    @HiltMoreFragment
    @Provides
    fun provideMoreAdapter(): BaseTypeListAdapter<BaseMoreModel> =
        BaseTypeListAdapter(
            mapOf(
                TYPE_HEADER to R.layout.item_more_header,
                TYPE_SUBJECT to R.layout.item_more_subject,
                TYPE_MENU to R.layout.item_more_menu,
                TYPE_BANNER to R.layout.item_more_banner,
                TYPE_BAR to R.layout.item_more_bar
            ),
            null
        )
}

@Module
@InstallIn(ViewModelComponent::class)
object MoreViewModelModule {

}