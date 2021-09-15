@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.example.testbank.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.testbank.R
import com.example.testbank.base.extension.dataBinding
import com.example.testbank.base.extension.toast
import com.example.testbank.base.google.EventObserver
import com.example.testbank.deviceapi.dialog.DialogInterface
import com.example.testbank.deviceapi.dialog.DialogModel
import com.example.testbank.deviceapi.open.OpenInterface
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity {
    constructor(): super()
    constructor(@LayoutRes resid: Int): super(resid)

    protected val binding : T by dataBinding()
    protected val disposable: CompositeDisposable = CompositeDisposable()

    @Inject lateinit var dialog: dagger.Lazy<DialogInterface>
    @Inject lateinit var open: dagger.Lazy<OpenInterface>

    /**
     * EventInterface 를 observe 한 뒤 onLiveEvent 로 전달 한다.
     */
    protected fun observeLiveEvents(liveEvent: EventInterface) {
        liveEvent.event.observe(this@BaseActivity, EventObserver {
            when (it.first) {
                EventInterface.EVENT_TOAST ->
                    toast(it.second.toString())

                EventInterface.EVENT_DIALOG ->
                    dialog.get().show(it.second as DialogModel)

                EventInterface.EVENT_FINISH ->
                    finish()

                EventInterface.ERROR_UNKNOWN -> {
                    dialog.get().show(DialogModel(
                        title = getString(R.string.common_error),
                        message = getString(R.string.common_unknown_error),
                        type = 0,
                        finish = true,
                        fromActivity = false
                    ))
                }

                EventInterface.ERROR_NETWORK -> {
                    val data = it.second as Pair<Boolean, String?>

                    dialog.get().show(DialogModel(
                        title = getString(R.string.common_error),
                        message = data.second ?: getString(R.string.common_network_error),
                        type = 0,
                        finish = data.first,
                        fromActivity = false
                    ))
                }

                EventInterface.ERROR_SERVER ->
                    toast(R.string.common_server_error)

                else ->
                    onLiveEvent(it.first, it.second)
            }
        })
    }

    open fun onLiveEvent(type: Int, data: Any?) {

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}

