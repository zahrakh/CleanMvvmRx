package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zahra.catawiki.catawikiapp.data.remote.Api
import com.zahra.catawiki.catawikiapp.domain.model.Pokemon
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons

data class PokemonResponseDto(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PokemonDto>,
) {
    fun toPokemonResponse(): Pokemons {
        return Pokemons(
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
            detailsUrl = url,
            imageUrl = Api.IMAGE_BASE_URL
        )
    }
}