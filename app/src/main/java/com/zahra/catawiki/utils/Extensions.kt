package com.zahra.catawiki.utils

import android.content.res.Resources
import android.view.View
import kotlin.math.roundToInt

fun View?.show() {
    this?.visibility = View.VISIBLE
}

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()


