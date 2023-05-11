package com.zahra.catawiki.catawikiapp.data.remote

import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import io.reactivex.Single


interface NetworkDataSource {

    fun getPokemonSpecies(): Single<PokemonResponseDto>

}