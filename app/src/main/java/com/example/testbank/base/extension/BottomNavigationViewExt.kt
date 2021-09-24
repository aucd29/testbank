package com.example.testbank.base.extension

import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.heartBeat(position: Int) {
    ((getChildAt(0) as ViewGroup)?.getChildAt(position) as ViewGroup)?.getChildAt(0).heartBeat(1)
}
