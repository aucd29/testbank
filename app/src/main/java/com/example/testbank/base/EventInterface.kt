package com.example.testbank.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testbank.base.google.Event
import com.example.testbank.deviceapi.dialog.DialogModel
import javax.inject.Inject

interface EventInterface {
    val event: LiveData<Event<Pair<Int, Any?>>>

    fun sendEvent(key: Int, value: Any)
    fun sendEvent(key: Int)

    fun toast(message: String) {
        sendEvent(EVENT_TOAST, message)
    }

    fun dialog(model: DialogModel) {
        sendEvent(EVENT_DIALOG, model)
    }

    fun dialog(message: String) {
        sendEvent(EVENT_DIALOG, DialogModel(message))
    }

    fun finish() {
        sendEvent(EVENT_FINISH)
    }

    fun unknownError(message: String = "") {
        sendEvent(ERROR_UNKNOWN, message)
    }

    fun networkError(finish: Boolean = true, message: String? = null) {
        sendEvent(ERROR_NETWORK, finish to message)
    }

    companion object {
        const val EVENT_TOAST  = 0X0001
        const val EVENT_DIALOG = 0X0002
        const val EVENT_FINISH = 0X0003
        const val EVENT_OPEN_CONTENT = 0X0005

        const val ERROR_UNKNOWN = 0X2001
        const val ERROR_NETWORK = 0X2002
        const val ERROR_SERVER = 0X2003
    }
}

class MyEvent @Inject constructor() : EventInterface {
    private val _event = MutableLiveData<Event<Pair<Int, Any?>>>()
    override val event: LiveData<Event<Pair<Int, Any?>>> = _event

    override fun sendEvent(key: Int, value: Any) {
        _event.value = Event(key to value)
    }

    override fun sendEvent(key: Int) {
        _event.value = Event(key to null)
    }
}