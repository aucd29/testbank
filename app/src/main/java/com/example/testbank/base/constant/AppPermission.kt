package com.example.testbank.base.constant

import android.Manifest

enum class AppPermission(val permission: String) {
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE),
}