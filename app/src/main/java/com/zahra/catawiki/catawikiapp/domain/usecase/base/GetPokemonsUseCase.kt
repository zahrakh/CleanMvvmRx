package com.zahra.catawiki.catawikiapp.domain.usecase.base

import com.zahra.catawiki.catawikiapp.domain.model.PokemonResponse
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
   private val repository: PokemonsRepository
):SingleUseCase<PokemonResponse>() {

    override fun buildUseCaseSingle(): Single<PokemonResponse> = repository.getPokemonSpecies()
}