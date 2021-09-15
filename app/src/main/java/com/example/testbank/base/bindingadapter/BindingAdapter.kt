@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.example.testbank.base.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieListener
import com.example.testbank.R
import timber.log.Timber
import java.io.File
import java.io.FileInputStream

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun View.setVisibleOrInvisible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}

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
