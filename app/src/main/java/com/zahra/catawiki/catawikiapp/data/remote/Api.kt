package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val IMAGE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }

    @GET("pokemon-species")
    fun getPokemonSpecies(): Single<PokemonResponseDto>

}