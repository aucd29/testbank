package com.example.testbank.base

import androidx.lifecycle.ViewModel
import com.markmount.wadiz.base.BaseTest

abstract class BaseViewModelTest<T: ViewModel> : BaseTest() {
    val viewmodel: T by lazy { createViewModel() }

    abstract fun createViewModel(): T
}