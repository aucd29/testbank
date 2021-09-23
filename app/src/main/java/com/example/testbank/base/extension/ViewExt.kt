package com.example.testbank.base.extension

import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    context.systemService<InputMethodManager>()?.hideSoftInputFromWindow(windowToken, 0)
}