@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.example.testbank.base.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

inline fun <reified T> Context.systemService(): T? =
    ContextCompat.getSystemService(this, T::class.java)

/**
 * display density 반환
 */
fun Context.displayDensity(): Float =
    resources.displayMetrics.density

fun Context.dpToPx(dp: Float): Int =
    (dp * displayDensity()).toInt()

fun Context.dpToPx(dp: Int): Int =
    (dp * displayDensity()).toInt()

fun Context.pxToDp(px: Float): Int =
    (px / displayDensity()).toInt()

fun Context.pxToDp(px: Int): Int =
    (px / displayDensity()).toInt()

/**
 * 토스트 메시지를 띄운다.
 *
 * @param message 메시지
 * @param duration toast duration
 * @return Toast object
 */
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT): Toast? =
    Toast.makeText(this, message, duration).apply {
        show()
    }

/**
 * 토스트 메시지를 띄운다.
 *
 * @param resid 메시지 아이디
 * @param duration toast duration
 * @return Toast object
 */
fun Context.toast(@StringRes resid: Int, duration: Int = Toast.LENGTH_SHORT): Toast? =
    Toast.makeText(this, getString(resid), duration).apply {
        show()
    }