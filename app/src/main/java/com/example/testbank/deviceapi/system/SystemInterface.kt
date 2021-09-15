package com.example.testbank.deviceapi.system

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.NotificationManagerCompat
import com.example.testbank.BuildConfig
import com.example.testbank.base.extension.systemService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

interface SystemInterface {
    /**
     * 앱 설정에 푸시 정보가 on/off 되어 있는지 확인한다.
     * @return on 이면 true, off 면 false
     */
    fun areNotificationsEnabled(): Boolean

    /**
     * 푸시 설정 창을 오픈 한다.
     * @param activityResult ActivityResultLauncher 객체
     */
    fun openNotificationSetting(activityResult: ActivityResultLauncher<Intent>)

    /**
     * 윈도우 크기를 반환 한다.
     * @return DisplayMetrics 객체
     */
    fun windowSize(): DisplayMetrics

    /**
     * 앱 레이블 명을 반환 한다.
     * @return 레이블 명
     */
    fun labelName(): String

    /**
     * 앱의 버전 명을 반환 한다.
     * @return 버전 명
     */
    fun versionName(): String

    /**
     * 랭귀지 테그 반환
     */
    fun languageTag(): String

    /**
     * 패키지 명 반환
     */
    fun packageName(): String

    /**
     * 앱버전 네임
     */
    fun appVersionName(): String

//    /**
//     * 현재 환경에서 런타임 퍼미션이 허락되어 있는지 확인 한다.
//     * @return 런타임 퍼미션이 허가되었으면 true 아니면 false
//     */
//    fun checkSelfPermissions(): Boolean
}

@Singleton
class MySystem @Inject constructor(
    @ApplicationContext val context: Context
) : SystemInterface {
    override fun areNotificationsEnabled(): Boolean =
        NotificationManagerCompat.from(context).areNotificationsEnabled()

    override fun openNotificationSetting(activityResult: ActivityResultLauncher<Intent>) {
        activityResult.launch(
            Intent().apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                } else {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    putExtra("app_package", context.packageName)
                    putExtra("app_uid", context.applicationInfo.uid)
                }
            }
        )
    }

    override fun windowSize(): DisplayMetrics {
        val dm = DisplayMetrics()
        context.systemService<WindowManager>()?.defaultDisplay?.getMetrics(dm)
        return dm
    }

    override fun labelName(): String =
        context.applicationInfo.loadLabel(context.packageManager).toString()

    override fun versionName(): String =
        BuildConfig.VERSION_NAME

    override fun languageTag(): String =
        Locale.getDefault().toLanguageTag().toLowerCase(Locale.getDefault())

    override fun packageName(): String =
        context.applicationInfo.packageName

    override fun appVersionName(): String {
        val info: PackageInfo
        try {
            info = context.packageManager.getPackageInfo(context.packageName, 0)
            return info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

//    override fun checkSelfPermissions(): Boolean {
//        for (each in AppPermission.values()) {
//            if (ActivityCompat.checkSelfPermission(context, each.permission) !=
//                PackageManager.PERMISSION_GRANTED) {
//                return false
//            }
//        }
//        return true
//    }
}