package com.zahra.catawiki.catawikiapp.domain.usecase

import com.zahra.catawiki.catawikiapp.domain.model.PokemonChainEvolution
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonChainEvolutionUseCaseDefault @Inject constructor(
    private val repository: PokemonsRepository,
) : GetPokemonChainEvolutionUseCase {

    override operator fun invoke(id: Int?): Single<PokemonChainEvolution> =
        repository.getPokemonChain(id)
}

interface GetPokemonChainEvolutionUseCase {
    operator fun invoke(id: Int?): Single<PokemonChainEvolution>
}

