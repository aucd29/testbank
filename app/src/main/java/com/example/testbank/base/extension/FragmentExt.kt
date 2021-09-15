package com.example.testbank.base.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT): Toast? =
    activity?.toast(message, duration)

fun Fragment.toast(@StringRes resid: Int, duration: Int = Toast.LENGTH_SHORT): Toast? =
    toast(getString(resid), duration)

/**
 * tag 기준으로 fragment manager 에서 원하는 fragment 를 찾는다.
 * (주의점 으로는 반드시 commit 시 tag 를 지정해 두어야 한다)
 *
 * @param T 찾아야 되는 Fragment class
 * @return
 */
inline fun <reified T> FragmentManager.find(): T? =
    findFragmentByTag(T::class.java.name) as T?