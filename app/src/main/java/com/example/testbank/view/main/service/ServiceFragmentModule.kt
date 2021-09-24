package com.example.testbank.view.main.service

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.base.decoration.VerticalMarginItemDecoration
import com.example.testbank.base.extension.dpToPx
import com.example.testbank.databinding.ItemServiceScrollBannerBinding
import com.example.testbank.repository.local.model.service.BaseServiceModel
import com.example.testbank.repository.local.model.service.ServiceScrollBannerItem
import com.google.android.material.tabs.TabLayout
import com.omidio.tabsyncedrecyclerview.LinearLayoutManagerWithSmoothScroller
import com.omidio.tabsyncedrecyclerview.TabSyncedRecyclerView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Named
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltServiceFragment

@Module
@InstallIn(FragmentComponent::class)
object ServiceFragmentModule {
    @HiltServiceFragment
    @Provides
    fun provideServiceAdapter(
        @Named("scrollbannerAdapter")
        bannerAdapter: BaseTypeListAdapter<ServiceScrollBannerItem>
    ): BaseTypeListAdapter<BaseServiceModel> =
        BaseTypeListAdapter(
            mapOf(
                BaseServiceModel.TYPE_SUBJECT to R.layout.item_service_subject,
                BaseServiceModel.TYPE_SCROLL_BANNER to R.layout.item_service_scroll_banner,
                BaseServiceModel.TYPE_MENU1 to R.layout.item_service_menu1,
                BaseServiceModel.TYPE_MENU2 to R.layout.item_service_menu2,
                BaseServiceModel.TYPE_BANNER to R.layout.item_service_banner,
            )
        ) { binding, _, item ->
            when (binding) {
                is ItemServiceScrollBannerBinding -> {
                    binding.itemServiceScrollbannerVp.apply {
                        if (adapter == null) {
                            adapter = bannerAdapter
                            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                                override fun onPageSelected(position: Int) {
                                    Timber.d("[SERVICE] CHANGE BANNER $position")
                                    binding.pageIndicator.selection = position
                                }
                            })

                            binding.pageIndicator.selection = 0
                        }
                    }
                }
            }
        }

    @Named("scrollbannerAdapter")
    @Provides
    fun provideFragmentPagerAdapter(): BaseTypeListAdapter<ServiceScrollBannerItem> =
        BaseTypeListAdapter(R.layout.item_service_large_banner)

    @HiltServiceFragment
    @Provides
    fun provideLayoutManager(fragment: Fragment) = LinearLayoutManagerWithSmoothScroller(
        fragment.requireContext(),
        RecyclerView.VERTICAL,
        false
    )

    @HiltServiceFragment
    @Provides
    fun provideMarginDecoration(@ApplicationContext context: Context) = VerticalMarginItemDecoration(
        lastSpaceBottom = context.dpToPx(200f)
    )
}