package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    @SerializedName("count")
    val count: Long=0,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PokemonDto>,
)

data class PokemonDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
)