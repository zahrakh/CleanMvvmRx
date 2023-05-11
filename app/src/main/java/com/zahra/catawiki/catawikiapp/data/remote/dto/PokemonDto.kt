package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zahra.catawiki.catawikiapp.domain.model.Pokemon
import com.zahra.catawiki.catawikiapp.domain.model.PokemonResponse

data class PokemonResponseDto(
    @SerializedName("count")
    val count: Long = 0,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PokemonDto>,
) {
    fun toPokemonResponse(): PokemonResponse {
        return PokemonResponse(
            count = count,
            next = next,
            results = results.map {
                it.toPokemon()
            }
        )
    }
}

data class PokemonDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
) {
    fun toPokemon(): Pokemon {
        return Pokemon(
            name = name,
            detailsUrl = url
        )
    }
}