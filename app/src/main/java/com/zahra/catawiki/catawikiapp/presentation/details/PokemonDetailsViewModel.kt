package com.zahra.catawiki.catawikiapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.model.PokemonDetails
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonDetailsUseCaseDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val getDetailsUseCase: GetPokemonDetailsUseCaseDefault
) : ViewModel() {

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var lastDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    fun getPokemonDetails(pageUrl: String) {
        _loading.postValue(true)
        lastDisposable = getDetailsUseCase.invoke(pageUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _loading.postValue(false)
                    _pokemonDetails.postValue(it)
                }, {
                    //todo check error type
                    _loading.postValue(false)
                    _error.postValue(it.message)
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