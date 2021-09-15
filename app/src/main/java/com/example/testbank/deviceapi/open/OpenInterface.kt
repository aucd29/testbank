package com.example.testbank.deviceapi.open

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import com.example.testbank.base.constant.AppPermission
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * 내부 activity/런타임 퍼미션을 열때 사용할 인터페이스
 */
interface OpenInterface {
    /**
     * 런타임 퍼미션을 연다.
     */
    fun runtimePermissions(activityResult: ActivityResultLauncher<Array<String>>)
    fun runtimePermissions(permissions: Array<String>, activityResult: ActivityResultLauncher<Array<String>>)
}

class MyOpen @Inject constructor(
    @ActivityContext context: Context
) : OpenInterface {
    override fun runtimePermissions(activityResult: ActivityResultLauncher<Array<String>>) {
        val permissions = arrayListOf<String>()

        AppPermission.values().forEach {
            permissions += it.permission
        }

        runtimePermissions(
            permissions.toTypedArray(),
            activityResult
        )
    }

    override fun runtimePermissions(
        permissions: Array<String>,
        activityResult: ActivityResultLauncher<Array<String>>
    ) {
        try {
            activityResult.launch(permissions)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}