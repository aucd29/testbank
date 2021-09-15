package com.example.testbank.deviceapi.dialog.roundtype

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import com.example.testbank.R
import com.example.testbank.databinding.DialogRoundBinding
import com.example.testbank.deviceapi.dialog.DialogModel

class RoundDialog constructor(
    private val activityContext: Context,
    private val model: DialogModel
) : AppCompatDialog(activityContext) {
    private lateinit var binding: DialogRoundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.attributes = WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }

        setCancelable(model.cancelable)
        val inflater = LayoutInflater.from(context)

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_round, null, false)
        binding.apply {
            item = model
            positive = View.OnClickListener {
                dismiss()
                model.positiveCallback?.invoke()
                if (model.finish && activityContext is Activity) {
                    activityContext.finish()
                }
            }
            negative = View.OnClickListener {
                dismiss()
                model.negativeCallback?.invoke()
            }
        }

        setContentView(binding.root)
    }
}