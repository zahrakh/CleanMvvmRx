package com.zahra.catawiki.catawikiapp.data.remote

import android.content.Context
import androidx.annotation.StringRes

class StringProviderDefault(
    private val appContext: Context,
) : StringProvider {
    override fun getString(@StringRes id: Int): String = appContext.getString(id)
}