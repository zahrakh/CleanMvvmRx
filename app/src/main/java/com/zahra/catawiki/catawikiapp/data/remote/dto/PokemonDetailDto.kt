package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
)
