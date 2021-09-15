package com.example.testbank.deviceapi.dialog

import android.app.Activity
import android.content.Context
import com.example.testbank.deviceapi.dialog.DialogInterface.Companion.DEFAULT
import com.example.testbank.deviceapi.dialog.roundtype.RoundDialog
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

interface DialogInterface {
    /**
     * 다이얼로그를 화면에 출력 한다.
     *
     * @param model
     */
    fun show(model: DialogModel)

    companion object {
        const val DEFAULT = 0
        const val TYPE_1 = 10
    }
}

class MyDialog @Inject constructor(
    @ActivityContext private val activityContext: Context
) : DialogInterface {
    override fun show(model: DialogModel) {
        if (activityContext is Activity && activityContext.isFinishing) {
            return
        }

        when (model.type) {
            DEFAULT ->
                RoundDialog(activityContext, model).apply {
                    show()
                }

            else ->
                error("Unknown dialog type")
        }
    }
}