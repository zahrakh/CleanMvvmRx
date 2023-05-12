package com.zahra.catawiki.catawikiapp.presentation.explore

import androidx.lifecycle.ViewModel
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.usecase.base.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {



}