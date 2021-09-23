package com.example.testbank.base.extension

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.annotation.ColorInt
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss"
private const val DATE_FORMAT_KOREAN = "yyyy년 MM월 dd일"
private const val TODAY_DAY_FORMAT = "yyyy.MM.dd"

@ColorInt
fun String?.toColor(): Int =
    if (!this.isNullOrEmpty()) {
        try {
            if (this.startsWith("#")) {
                Color.parseColor(this)
            } else {
                Color.parseColor("#${this}")
            }
        } catch (e: Exception) {
            Color.TRANSPARENT
        }
    } else {
        Color.TRANSPARENT
    }

/**
 * 서버 date 포멧인
 * date: Tue, 09 Feb 2021 09:00:57 GMT 를
 * yyyy년 MM월 dd일 로 변경 해준다.
 *
 * @param format 변환할 데이터 포맷을 정의 힌다, 기본 값 (yyyy년 MM월 dd일)
 * @return yyyy년 MM월 dd일 형태의 날짜 문자열 값
 */
@SuppressLint("SimpleDateFormat")
fun String.toTimeFormat(
    format: String = DATE_FORMAT_KOREAN,
    pattern: String = PATTERN_ISO
): String? =
    try {
        SimpleDateFormat(format).format(
            SimpleDateFormat(
                pattern,
                Locale.ENGLISH
            ).parse(this))
    } catch (e: ParseException) {
        Timber.e(e)
        null
    }

/**
 * "2020-04-10T10:30:57" 와 같은 ISO 로컬 타임 형태의 문자열을 파싱해서
 * 오늘의 날짜로 반환 한다.
 * @return "2020.04.10" 형태의 문자열 반환
 */
fun String?.isoStringToTodayString(pattern: String = PATTERN_ISO): String? =
    try {
        this?.toTimeFormat(
            TODAY_DAY_FORMAT,
            pattern
        )
    } catch (e: java.lang.Exception) {
        Timber.e(e)
        null
    }

fun String?.toTimeMills(pattern: String = PATTERN_ISO): Long =
    try {
        SimpleDateFormat(pattern).parse(this).time
    } catch (e: Exception) {
        0
    }