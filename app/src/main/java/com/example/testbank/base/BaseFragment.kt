package com.example.testbank.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.testbank.R
import com.example.testbank.base.extension.toast
import com.example.testbank.base.google.EventObserver
import com.example.testbank.deviceapi.dialog.DialogInterface
import com.example.testbank.deviceapi.dialog.DialogModel
import com.example.testbank.deviceapi.open.OpenInterface
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BaseFragment<T: ViewDataBinding>(
    @LayoutRes val layoutResid: Int
) : Fragment() {
    protected lateinit var binding: T
    protected val disposable: CompositeDisposable = CompositeDisposable()

    @Inject lateinit var dialog: dagger.Lazy<DialogInterface>
    @Inject lateinit var open: dagger.Lazy<OpenInterface>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResid, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    protected fun observeLiveEvents(liveEvent: EventInterface) {
        liveEvent.event.observe(viewLifecycleOwner, EventObserver {
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
                    toast(getString(R.string.common_server_error))

                else ->
                    onLiveEvent(it.first, it.second)
            }
        })
    }

    open fun onLiveEvent(cmd: Int, data: Any?) {
    }

    protected fun finish() {
        // 스택이 없을 경우 액티비티를 종료하도록 수정 [aucd29][2021/08/20]
        if (parentFragmentManager.backStackEntryCount == 0) {
            activity?.finish()
        } else {
            parentFragmentManager.popBackStack()
        }
    }
}