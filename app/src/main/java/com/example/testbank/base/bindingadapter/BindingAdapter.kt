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

////////////////////////////////////////////////////////////////////////////////////
//
// LOTTIE
//
////////////////////////////////////////////////////////////////////////////////////

@BindingAdapter("lottiePath")
fun LottieAnimationView.lottiePath(name: String?) {
    name?.let {
        val filePath = "${context.filesDir}/lottie/$name"
        val fp = File(filePath)
        if (!fp.exists()) {
            Timber.e("[LOTTIE] ERROR FILE NOT FOUND")
            setAnimation(R.raw.default_lottie)
            return
        }

        // https://github.com/airbnb/lottie-android/issues/999
        val task = LottieCompositionFactory.fromJsonInputStream(FileInputStream(fp), name)
        val listener = object: LottieListener<LottieComposition> {
            override fun onResult(result: LottieComposition) {
                Timber.d("[LOTTIE] OK")
                setComposition(result)
                task.removeListener(this)
            }
        }
        val failureListener = object: LottieListener<Throwable> {
            override fun onResult(result: Throwable) {
                Timber.e("[LOTTIE] ERROR ${result.message}")
                // 잘못된 파일을 삭제해 새롭게 다운로드하도록 유도 한다.
                fp.delete()
                setAnimation(R.raw.default_lottie)
                task.removeFailureListener(this)
            }
        }

        task.apply {
            addListener(listener)
            addFailureListener(failureListener)
        }
    } ?: setAnimation(R.raw.default_lottie)
}

////////////////////////////////////////////////////////////////////////////////////
//
// GLIDE
//
////////////////////////////////////////////////////////////////////////////////////

@BindingAdapter("imageUri")
fun ImageView.setImageUri(uri: String?) {
    if (uri.isNullOrEmpty().not()) {
        val newUrl = uri!!.toLowerCase(Locale.getDefault())
        if (!(newUrl.startsWith("https") || newUrl.startsWith("http"))) {
            setImageId(uri)
        } else {
            GlideApp.with(this.context).load(uri)
                .thumbnail(
                    GlideApp.with(context)
                        .load(R.drawable.shape_place_holder).error(
                            GlideApp.with(this.context).load(uri)
                        )
                )
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(this)
        }
    } else {
        setImageDrawable(null)
    }
}

@BindingAdapter("imageId", requireAll = false)
fun ImageView.setImageId(imageId: String?) {
    val imagesId = imageId?.let {
        context.resources.getIdentifier(it, "drawable", context.packageName)
    }

    imagesId?.let { id ->
        if (id != -1) {
            GlideApp.with(this.context)
                .load(id)
                .centerInside()
                .into(this)
        }
    } ?: setImageDrawable(null)
}
