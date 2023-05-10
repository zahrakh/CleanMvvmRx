package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("count")
    val count: Long=0,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>,
)

data class Result(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
)