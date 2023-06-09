package com.zahra.catawiki.catawikiapp.domain.repository

import com.zahra.catawiki.catawikiapp.domain.model.PokemonChainEvolution
import com.zahra.catawiki.catawikiapp.domain.model.PokemonDetails
import com.zahra.catawiki.catawikiapp.domain.model.PokemonRate
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import io.reactivex.Single

interface PokemonsRepository {
    fun getPokemonSpecies(pageUrl: String?): Single<Pokemons>
    fun getPokemonDetails(pageUrl: String?): Single<PokemonDetails>
    fun getPokemonChain(id: Int?): Single<PokemonChainEvolution>
    fun getPokemonRate(id: Int?): Single<PokemonRate>
}