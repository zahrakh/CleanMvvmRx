package com.zahra.catawiki.catawikiapp.domain.usecase

import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonsUseCaseDefault @Inject constructor(
    private val repository: PokemonsRepository
) : GetPokemonsUseCase {

    override operator fun invoke(pageUrl: String?): Single<Pokemons> {
        return repository.getPokemonSpecies(pageUrl)
    }
}

interface GetPokemonsUseCase {
    operator fun invoke(pageUrl: String?): Single<Pokemons>
}