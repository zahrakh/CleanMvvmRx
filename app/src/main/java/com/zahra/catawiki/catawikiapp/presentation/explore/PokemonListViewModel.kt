package com.zahra.catawiki.catawikiapp.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zahra.catawiki.R
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.lang.annotation.ElementType
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

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private var lastDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()
    private var nextUrl: String? = null

    init {
        getPokemons()
    }

    fun getPokemons() {
        _loading.postValue(true)
        _error.postValue(false)
        lastDisposable = getPokemonsUseCase.invoke(nextUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    nextUrl = it.next
                    _loading.postValue(false)
                    _pokemonList.postValue(it)
                }, {
                    _loading.postValue(false)
                    _error.postValue(true)
                    _errorMessage.postValue(handleException(it))
                }
            )
        lastDisposable?.let {
            compositeDisposable.add(it)
        }

    }

    private fun handleException(it: Throwable?): String {
        return when (it) {
            is HttpException -> {
                it.message ?: stringProvider.getString(R.string.check_internet_connection)
            }
            is IOException -> {
                it.message ?: stringProvider.getString(R.string.error_occurred)
            }
            else -> {
                it?.message ?: stringProvider.getString(R.string.unknown_error)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}