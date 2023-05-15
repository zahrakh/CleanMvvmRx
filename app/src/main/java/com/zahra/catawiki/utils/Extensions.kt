package com.zahra.catawiki.utils

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlin.math.roundToInt

//UI extension Function

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.showHide(it: Boolean) {
    if (it) {
        this?.visibility = View.VISIBLE
    } else {
        this?.visibility = View.INVISIBLE
    }
}

fun View?.showGone(it: Boolean) {
    if (it) {
        this?.visibility = View.VISIBLE
    } else {
        this?.visibility = View.GONE
    }
}

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()


//Fragment Extension Function

fun Fragment.safeNavigate(@IdRes resId: Int, args: Bundle? = null) {
    try {
        val navController = view?.findNavController()
        if (navController?.currentDestination?.id == resId) return

        navController?.navigate(resId, args)
    } catch (ignore: Exception) {
        Log.d("NavigationTest", ignore.toString())
    }
}

