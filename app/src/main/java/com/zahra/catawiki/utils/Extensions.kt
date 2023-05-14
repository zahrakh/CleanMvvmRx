package com.zahra.catawiki.utils

import android.content.Intent
import android.content.res.Resources
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
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

//Generic Object

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

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

