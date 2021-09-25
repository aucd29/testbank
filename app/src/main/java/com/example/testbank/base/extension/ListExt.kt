package com.example.testbank.base.extension

fun <T> List<T>.makeCircularList(): List<T> {
    return toMutableList().apply {
        if (size > 1) {
            add(0, get(lastIndex))
            add(get(1))
        }
    }
}