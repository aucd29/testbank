package com.example.testbank.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testbank.view.main.alarm.AlarmFragment
import com.example.testbank.view.main.home.HomeFragment
import com.example.testbank.view.main.more.MoreFragment
import com.example.testbank.view.main.service.ServiceFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltMainActivity

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {
    @HiltMainActivity
    @Provides
    fun provideFragmentPagerAdapter(activity: FragmentActivity) =
        object: FragmentPagerAdapter(activity.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            // override fun getItemCount(): Int =
            //     4
            //
            // override fun createFragment(position: Int): Fragment =
            //     when (position) {
            //         0 ->
            //             HomeFragment()
            //
            //         1 ->
            //             ServiceFragment()
            //
            //         2 ->
            //             AlarmFragment()
            //
            //         else ->
            //             MoreFragment()
            //     }

            override fun getCount(): Int =
                4

            override fun getItem(position: Int): Fragment =
                when (position) {
                    0 ->
                        HomeFragment()

                    1 ->
                        ServiceFragment()

                    2 ->
                        AlarmFragment()

                    else ->
                        MoreFragment()
                }

        }
            
    @Module
    @InstallIn(ActivityComponent::class)
    interface BindModule {
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
}