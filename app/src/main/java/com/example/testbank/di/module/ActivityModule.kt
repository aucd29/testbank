package com.example.testbank.di.module

import com.example.testbank.deviceapi.dialog.DialogInterface
import com.example.testbank.deviceapi.dialog.MyDialog
import com.example.testbank.deviceapi.open.MyOpen
import com.example.testbank.deviceapi.open.OpenInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Module
    @InstallIn(ActivityComponent::class)
    interface BindModule {
        @Binds
        fun bindDialogInterface(dialog: MyDialog): DialogInterface

        @Binds
        fun bindOpenInterface(open: MyOpen): OpenInterface
    }
}