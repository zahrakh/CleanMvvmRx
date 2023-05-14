package com.zahra.catawiki.catawikiapp.data.repository

import com.zahra.catawiki.catawikiapp.data.remote.NetworkDataSource
import com.zahra.catawiki.catawikiapp.domain.model.PokemonDetails
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import javax.inject.Inject

class PokemonsRepositoryDefault @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : PokemonsRepository {

    override fun getPokemonSpecies(pageUrl: String?): Single<Pokemons> =
        networkDataSource.getPokemonSpecies(pageUrl).map { it.toPokemonResponse() }

    override fun getPokemonDetails(pageUrl: String?): Single<PokemonDetails> =
        networkDataSource.getPokemonDetails(pageUrl).map { it.toPokemonDetails() }
}