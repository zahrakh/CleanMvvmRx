package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonChainEvolutionDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonDetailDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonRateDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface Api {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val IMAGE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }

    @GET("pokemon-species")
    fun getPokemonSpecies(): Single<PokemonResponseDto>

    @GET
    fun getPokemonSpeciesWithUrl(@Url pageUrl:String): Single<PokemonResponseDto>

    @GET
    fun getPokemonDetails(@Url pageUrl:String?): Single<PokemonDetailDto>

    @GET("evolution-chain/{chain_id}")
    fun getPokemonChain(
        @Path("chain_id")id: Int?
    ): Single<PokemonChainEvolutionDto>


    @GET("pokemon-species/{pokemon_id}")
    fun getPokemonRate(
        @Path("pokemon_id")id: Int?
    ): Single<PokemonRateDto>
}