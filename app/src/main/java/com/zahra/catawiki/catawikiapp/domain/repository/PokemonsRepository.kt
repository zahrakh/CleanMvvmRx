package com.zahra.catawiki.catawikiapp.domain.repository

import com.zahra.catawiki.catawikiapp.domain.model.PokemonResponse
import io.reactivex.Single

interface PokemonsRepository {
    fun getPokemonSpecies(): Single<PokemonResponse>

}