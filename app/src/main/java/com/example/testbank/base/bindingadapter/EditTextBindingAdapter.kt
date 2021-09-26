package com.example.testbank.base.bindingadapter

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("editorAction")
fun EditText.bindEditorAction(callback: (String) -> Boolean) {
    setOnEditorActionListener { _, id, event ->
        when (id) {
            EditorInfo.IME_ACTION_DONE,
            EditorInfo.IME_ACTION_SEARCH ->
                callback(text.toString())

            EditorInfo.IME_ACTION_UNSPECIFIED -> {
                //https://pk09.tistory.com/entry/custom-IME%EC%97%90%EC%84%9C-enterdone-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EB%B0%98%EC%9D%91%EC%9D%B4-%EC%97%86%EC%9D%84%EB%95%8C
                when(event.keyCode) {
                    KeyEvent.KEYCODE_ENTER ->
                        callback(text.toString())

                    else -> false
                }
            }

            else -> false
        }
    }
}