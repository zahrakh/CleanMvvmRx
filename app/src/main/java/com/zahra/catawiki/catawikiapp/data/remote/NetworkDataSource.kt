package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonChainEvolutionDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonDetailDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonRateDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import io.reactivex.Single


interface NetworkDataSource {

    fun getPokemonSpecies(pageUrl: String?): Single<PokemonResponseDto>
    fun getPokemonDetails(pageUrl: String?): Single<PokemonDetailDto>
    fun getPokemonChain(id: Int?): Single<PokemonChainEvolutionDto>
    fun getPokemonRate(id: Int?): Single<PokemonRateDto>

}