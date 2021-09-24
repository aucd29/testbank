package com.example.testbank.view.main.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testbank.view.main.home.mystore.MyStoreFragment
import com.example.testbank.view.main.home.search.HomeFragmentString
import com.example.testbank.view.main.home.search.HomeFragmentStringInterface
import com.example.testbank.view.main.home.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltHomeFragment

@Module
@InstallIn(FragmentComponent::class)
object HomeFragmentModule {
    @HiltHomeFragment
    @Provides
    fun provideFragmentPagerAdapter(fragment: Fragment) =
        object: FragmentStateAdapter(fragment) {
            override fun getItemCount(): Int =
                2

            override fun createFragment(position: Int): Fragment =
                when (position) {
                    0 -> SearchFragment()
                    else -> MyStoreFragment()
                }
        }

    @Module
    @InstallIn(FragmentComponent::class)
    interface BindModule {
        @Binds
        fun bindHomeFragmentStringInterface(string: HomeFragmentString): HomeFragmentStringInterface
    }
}
