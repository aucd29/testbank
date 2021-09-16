package com.example.testbank.base.extension

import android.graphics.Color
import androidx.annotation.ColorInt


@ColorInt
fun String?.toColor(): Int =
    if (!this.isNullOrEmpty()) {
        try {
            if (this.startsWith("#")) {
                Color.parseColor(this)
            } else {
                Color.parseColor("#${this}")
            }
        } catch (e: Exception) {
            Color.TRANSPARENT
        }
    } else {
        Color.TRANSPARENT
    }
