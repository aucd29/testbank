@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.example.testbank.base.extension

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

/**
 * Moshi 이용을 단일화 한다.
 *
 * @param T 타입
 * @param type 리스트/맵과 같은 세부 타입이 필요시 설정 할 값
 * @return 생성된 json adapter
 */
inline fun <reified T> moshi(type: ParameterizedType? = null): JsonAdapter<T> =
    if (type == null) {
        moshiBuild().adapter(T::class.java)
    } else {
        moshiBuild().adapter(type)
    }

/**
 * Moshi 객체 반환
 *
 * @return moshi 객체
 */
inline fun moshiBuild(): Moshi =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

