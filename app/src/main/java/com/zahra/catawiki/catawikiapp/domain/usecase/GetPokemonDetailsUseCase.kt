package com.zahra.catawiki.catawikiapp.domain.usecase

import com.zahra.catawiki.catawikiapp.domain.model.PokemonDetails
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonDetailsUseCaseDefault @Inject constructor(
    private val repository: PokemonsRepository
) : GetPokemonDetailsUseCase {

    override operator fun invoke(pageUrl: String?): Single<PokemonDetails> {
        return repository.getPokemonDetails(pageUrl)
    }
}

interface GetPokemonDetailsUseCase {
    operator fun invoke(pageUrl: String?): Single<PokemonDetails>
}