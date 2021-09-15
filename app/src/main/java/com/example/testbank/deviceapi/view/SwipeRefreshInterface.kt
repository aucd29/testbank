package com.example.testbank.deviceapi.view

import androidx.databinding.ObservableBoolean
import com.example.testbank.deviceapi.vibrator.VibratorInterface
import javax.inject.Inject

interface SwipeRefreshInterface {
    val isSwipeRefreshing: ObservableBoolean
    fun onSwipeRefresh()
    fun doneSwipeRefresh()
}

class MySwipeRefresh @Inject constructor(
    private val vibrator: VibratorInterface
) : SwipeRefreshInterface {
    override val isSwipeRefreshing: ObservableBoolean =
        ObservableBoolean(false)

    override fun onSwipeRefresh() {
        if (isSwipeRefreshing.get().not()) {
            isSwipeRefreshing.set(true)
        }
        vibrator.vibrateLevel(VibratorInterface.IMPACT_MEDIUM)
    }

    override fun doneSwipeRefresh() {
        isSwipeRefreshing.set(false)
    }
}