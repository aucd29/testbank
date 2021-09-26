@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.example.testbank.base.bindingadapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieListener
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.testbank.R
import com.example.testbank.base.GlideApp
import com.example.testbank.base.extension.toColor
import net.cachapa.expandablelayout.ExpandableLayout
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.util.*

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun View.setVisibleOrInvisible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("backgroundColor")
fun View.setBackgroundColor(color: String?) {
    setBackgroundColor(
        color?.let {
            it.toColor()
        } ?: android.R.color.transparent
    )
}

@BindingAdapter("backgroundTint")
fun View.setBackgroundTint(color: String?) {
    color?.let {
        setBackgroundTintList(ColorStateList.valueOf(it.toColor()))
    }
}

@BindingAdapter("drawableTint")
fun TextView.setDrawableTint(color: String?) {
    color?.let {
        for (drawable in this.compoundDrawablesRelative) {
            drawable?.mutate()
            drawable?.colorFilter = PorterDuffColorFilter(
                it.toColor(), PorterDuff.Mode.SRC_IN
            )
        }
    }
}

@BindingAdapter("textColor")
fun TextView.setTextColor(color: String?) {
    color?.let {
        setTextColor(it.toColor())
    }
}
