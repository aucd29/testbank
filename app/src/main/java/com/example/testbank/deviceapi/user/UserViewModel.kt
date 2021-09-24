package com.example.testbank.deviceapi.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
) : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    fun init() {
        _userName.value = "아무개 >"
    }
}