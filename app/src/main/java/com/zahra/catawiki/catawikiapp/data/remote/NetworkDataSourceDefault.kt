package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import io.reactivex.Single

class NetworkDataSourceDefault(
    private val api: Api,
) : NetworkDataSource {

    override fun getPokemonSpecies(): Single<PokemonResponseDto> = api.getPokemonSpecies()
}