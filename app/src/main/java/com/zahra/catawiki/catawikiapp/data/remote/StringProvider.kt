package com.zahra.catawiki.catawikiapp.data.remote

import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes id: Int): String
}