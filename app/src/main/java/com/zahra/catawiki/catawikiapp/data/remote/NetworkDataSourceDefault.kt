package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonDetailDto
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
}