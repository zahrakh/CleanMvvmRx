package com.zahra.catawiki.catawikiapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val stringProvider: StringProvider,

    ) : ViewModel() {

    private val _pokemonDetails = MutableLiveData<Pokemons>()
    val pokemonDetails: LiveData<Pokemons> = _pokemonDetails

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var lastDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

}