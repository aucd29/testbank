package com.markmount.wadiz.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule


abstract class BaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    open fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    open fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}