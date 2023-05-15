package com.zahra.catawiki.catawikiapp.domain.usecase

import com.zahra.catawiki.catawikiapp.domain.model.PokemonRate
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonRateUseCaseDefault @Inject constructor(
    private val repository: PokemonsRepository
) : GetPokemonRateUseCase {

    override operator fun invoke(id: Int?): Single<PokemonRate> {
        return repository.getPokemonRate(id)
    }
}

interface GetPokemonRateUseCase {
    operator fun invoke(id: Int?): Single<PokemonRate>
}