package com.example.testbank.deviceapi.dialog

import android.view.View
import androidx.annotation.DrawableRes
import java.io.Serializable
import com.example.testbank.deviceapi.dialog.DialogInterface.Companion.DEFAULT

data class DialogModel (
    val message: String,
    var title: String? = null,
    val positiveCallback: (() -> Unit)? = null,
    val negativeCallback: (() -> Unit)? = null,
    val view: View? = null,
    val type: Int = DEFAULT,
    val cancelable: Boolean = false,
    val positiveButtonText: String? = null,
    val negativeButtonText: String? = null,
    @DrawableRes var image: Int? = null,
    var finish: Boolean = false,
    var fromActivity: Boolean = true,
    var any: Any? = null
) : Serializable
