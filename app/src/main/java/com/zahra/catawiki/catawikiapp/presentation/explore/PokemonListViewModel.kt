package com.zahra.catawiki.catawikiapp.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _pokemonList = MutableLiveData<Pokemons>()
    val pokemonList: LiveData<Pokemons> = _pokemonList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private var lastDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()
    private var nextUrl: String? = null

    init {
        getPokemons(nextUrl)
    }

    fun getPokemons(
        url: String? = null
    ) {
        _loading.postValue(true)
        lastDisposable = getPokemonsUseCase.invoke(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    nextUrl = it.next
                    _loading.postValue(false)
                    _pokemonList.postValue(it)
                }, {
                    //todo check error type
                    _loading.postValue(false)
                    _error.postValue(true)
                }
            )
        lastDisposable?.let {
            compositeDisposable.add(it)
        }

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}