package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonChainEvolutionDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonDetailDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonRateDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import io.reactivex.Single

class NetworkDataSourceDefault(
    private val api: Api,
) : NetworkDataSource {

    override fun getPokemonSpecies(pageUrl: String?): Single<PokemonResponseDto> =
        if (pageUrl.isNullOrEmpty()) {
            api.getPokemonSpecies()
        } else {
            api.getPokemonSpeciesWithUrl(pageUrl)
        }


    override fun getPokemonDetails(pageUrl: String?): Single<PokemonDetailDto> =
        api.getPokemonDetails(pageUrl)

    override fun getPokemonChain(id: Int?): Single<PokemonChainEvolutionDto> =
        api.getPokemonChain(id)

    override fun getPokemonRate(id: Int?): Single<PokemonRateDto> =
        api.getPokemonRate(id)
}