package com.example.testbank.base.extension

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 화면에서 가상 키보드를 종료 시킨다.
 */
fun View.hideKeyboard() {
    context.systemService<InputMethodManager>()?.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * 뷰에 heart beat 애니메이션 효과를 낸다.
 *
 * @param repeatCount 애니메이션 효과를 반복할 횟수 (기본값은 무제한)
 * @param scale 커질 크기 (기본 1.085)
 * @param duration 애니메이션 동작 시간 (기본 150)
 */
fun View.heartBeat(
    repeatCount: Int = 0,
    scale: Float = 1.085f,
    duration: Long = 150
) {
    ObjectAnimator.ofPropertyValuesHolder(this,
        PropertyValuesHolder.ofFloat("scaleX", scale),
        PropertyValuesHolder.ofFloat("scaleY", scale)
    ).apply {
        this.duration = duration
        this.repeatMode = ObjectAnimator.REVERSE
        this.repeatCount = repeatCount
        this.start()
    }
}