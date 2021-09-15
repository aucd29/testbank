package com.example.testbank.deviceapi.vibrator

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.IntDef
import com.example.testbank.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


/**
 * iOS 에 Haptic 관련 정보 https://wiki.wadizcorp.com/pages/viewpage.action?pageId=52050057
 */
interface VibratorInterface {
    /**
     * 단말에 진동을 일으킨다
     * @param milliseconds 진동 시간 (기본값 350ms)
     */
    fun vibrate(milliseconds: Long = 350L)

    /**
     * 단말에 진동을 일으킨다
     * @param pattern 진동 패턴
     * @param amplitude 모터 세기
     */
    fun vibrate(pattern: LongArray, amplitude: IntArray)

    /**
     * 단말에 진동을 일으킨다.
     * @param iOS 와 동일한 진동 형태 [1 ~ 7]
     */
    fun vibrateLevel(level: Int)

    companion object {
        const val IMPACT_LIGHT = 1
        const val IMPACT_MEDIUM = 2
        const val IMPACT_HEAVY = 3
        const val NOTIFICATION_SUCCESS = 4
        const val NOTIFICATION_ERROR = 5
        const val NOTIFICATION_WARNING = 6
        const val SELECTION = 7
    }
}

@IntDef(
    VibratorInterface.IMPACT_LIGHT,
    VibratorInterface.IMPACT_MEDIUM,
    VibratorInterface.IMPACT_HEAVY,
    VibratorInterface.NOTIFICATION_SUCCESS,
    VibratorInterface.NOTIFICATION_ERROR,
    VibratorInterface.NOTIFICATION_WARNING,
    VibratorInterface.SELECTION
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class VibratorLevel

@Singleton
class MyVibrator @Inject constructor(
    @ApplicationContext private val context: Context
) : VibratorInterface {
    /**
     * 단말에 진동을 일으킨다
     * @param milliseconds 진동 시간
     */
    override fun vibrate(milliseconds: Long) {
        vibrator(context)?.let {
            try {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
                    it.vibrate(
                        VibrationEffect.createOneShot(milliseconds,
                        VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    it.vibrate(milliseconds)
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }

                Timber.e(e)
            }
        }
    }

    /**
     * 단말에 진동을 일으킨다
     * @param pattern 진동 패턴
     * @param amplitude 모터 세기
     */
    override fun vibrate(pattern: LongArray, amplitude: IntArray) {
        vibrator(context)?.let {
            try {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
                    it.vibrate(
                        VibrationEffect.createWaveform(pattern, amplitude,
                        VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    it.vibrate(pattern, -1)
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }

                Timber.e(e)
            }
        }
    }

    fun vibrate(predefine: String) {
        // 나중을 위한 코드 (api level 29 부터 지원하는 부분)
        vibrator(context)?.let {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                it.vibrate(
                    VibrationEffect.createPredefined(when (predefine) {
                    "tick"        -> VibrationEffect.EFFECT_TICK
                    "click"       -> VibrationEffect.EFFECT_CLICK
                    "heavyClick"  -> VibrationEffect.EFFECT_HEAVY_CLICK
                    "doubleClick" -> VibrationEffect.EFFECT_DOUBLE_CLICK
                    else          -> VibrationEffect.EFFECT_TICK
                }))
            }
        }
    }

    /**
     * 단말에 진동을 일으킨다.
     * @param level iOS 와 유사한 패턴을 이용한다. 단 api level 25 이상 부터 모터의 세기 옵션 동작 한다.
     */
    override fun vibrateLevel(@VibratorLevel level: Int) {
        // 패턴 처리 방식이 달라 특정 버전을 분기 처리한다. [aucd29][2020/10/20]
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            when (level) {
                VibratorInterface.IMPACT_LIGHT ->
                    vibrate(longArrayOf(VIBRATE_TIME), intArrayOf(MOTOR_LIGHT))

                VibratorInterface.IMPACT_MEDIUM ->
                    vibrate(longArrayOf(VIBRATE_TIME), intArrayOf(MOTOR_MEDIUM))

                VibratorInterface.IMPACT_HEAVY ->
                    vibrate(longArrayOf(VIBRATE_TIME), intArrayOf(MOTOR_HEAVY))

                VibratorInterface.NOTIFICATION_SUCCESS ->
                    vibrate(longArrayOf(VIBRATE_TIME, VIBRATE_DELAY, VIBRATE_TIME),
                        intArrayOf(MOTOR_MEDIUM, 0, MOTOR_MEDIUM))

                VibratorInterface.NOTIFICATION_ERROR ->
                    vibrate(longArrayOf(VIBRATE_TIME, VIBRATE_DELAY, VIBRATE_TIME, VIBRATE_DELAY, VIBRATE_TIME),
                        intArrayOf(MOTOR_MEDIUM, 0, MOTOR_MEDIUM, 0, MOTOR_MEDIUM))

                VibratorInterface.NOTIFICATION_WARNING ->
                    vibrate(longArrayOf(VIBRATE_TIME, VIBRATE_LONG_DELAY, VIBRATE_TIME),
                        intArrayOf(MOTOR_MEDIUM, 0, MOTOR_MEDIUM))

                VibratorInterface.SELECTION ->
                    vibrate(longArrayOf(VIBRATE_TIME), intArrayOf(30))
            }
        } else {
            when (level) {
                VibratorInterface.IMPACT_LIGHT ->
                    vibrate(15)

                VibratorInterface.IMPACT_MEDIUM ->
                    vibrate(30)

                VibratorInterface.IMPACT_HEAVY ->
                    vibrate(70)

                VibratorInterface.NOTIFICATION_SUCCESS ->
                    vibrate(longArrayOf(0, VIBRATE_TIME, VIBRATE_DELAY, VIBRATE_TIME),
                        intArrayOf())

                VibratorInterface.NOTIFICATION_ERROR ->
                    vibrate(longArrayOf(0, VIBRATE_TIME, VIBRATE_DELAY, VIBRATE_TIME, VIBRATE_DELAY, VIBRATE_TIME),
                        intArrayOf())

                VibratorInterface.NOTIFICATION_WARNING ->
                    vibrate(longArrayOf(0, VIBRATE_TIME, VIBRATE_LONG_DELAY, VIBRATE_TIME),
                        intArrayOf())

                VibratorInterface.SELECTION ->
                    vibrate(40)
            }
        }
    }

    private fun vibrator(context: Context): Vibrator? =
        context.getSystemService(Context.VIBRATOR_SERVICE)?.let {
            it as Vibrator
        }

    companion object {
        const val MOTOR_LIGHT = 40
        const val MOTOR_MEDIUM = 120
        const val MOTOR_HEAVY = 200

        const val VIBRATE_TIME = 30L
        const val VIBRATE_DELAY = 150L
        const val VIBRATE_LONG_DELAY = 250L
    }
}