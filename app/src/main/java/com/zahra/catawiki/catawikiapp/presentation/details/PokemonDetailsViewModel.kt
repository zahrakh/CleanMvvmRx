package com.zahra.catawiki.catawikiapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zahra.catawiki.R
import com.zahra.catawiki.catawikiapp.data.remote.Api
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.model.Pokemon
import com.zahra.catawiki.catawikiapp.domain.model.PokemonChainEvolution
import com.zahra.catawiki.catawikiapp.domain.model.PokemonDetails
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonChainEvolutionUseCase
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonDetailsUseCase
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val getDetailsUseCase: GetPokemonDetailsUseCase,
    private val chainUseCase: GetPokemonChainEvolutionUseCase,
    private val rateUseCae: GetPokemonRateUseCase
) : ViewModel() {

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails

    private val _chainAndRate = MutableLiveData<PokemonChainEvolution?>()
    val chainAndRate: LiveData<PokemonChainEvolution?> = _chainAndRate

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val compositeDisposable = CompositeDisposable()
    lateinit var selectedPokemonDetails: PokemonDetails


    fun setSelectedPokemon(pokemon: Pokemon?) {
        selectedPokemonDetails = PokemonDetails(
            name = pokemon?.name,
            description = "",
            captureRate = 0,
            imageUrl = pokemon?.imageUrl + pokemon?.id + ".png",
        )
        getPokemonDetails(pokemon?.detailsUrl)
        getPokemonChainAndRate(pokemon?.id ?: return)
    }

    private fun getPokemonDetails(pageUrl: String?) {
        _loading.postValue(true)
        getDetailsUseCase.invoke(pageUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _loading.postValue(false)
                    _pokemonDetails.postValue(selectedPokemonDetails.apply {
                        this.description = it.description
                    })
                }, {
                    _loading.postValue(false)
                    _error.postValue(it.message)
                }
            ).let {
                compositeDisposable.add(it)
            }
    }

    private fun getPokemonChainAndRate(id: Int) {
        var result: PokemonChainEvolution? = null
        chainUseCase.invoke(id)
            .subscribeOn(Schedulers.io())
            .flatMap { species ->
                result = species.copy()
                //todo handle invalid id
                val firstEvolutionSpeciesId = fetchIdFromUrl(species.chain?.species?.url)
                result?.ImageUrl = Api.IMAGE_BASE_URL + firstEvolutionSpeciesId + ".png"
                rateUseCae.invoke(firstEvolutionSpeciesId)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _chainAndRate.postValue(result.apply {
                    this?.speciesRate = (_pokemonDetails.value?.captureRate ?: 0) - it.captureRate
                })
            }, {
                _error.postValue(it.message ?: stringProvider.getString(R.string.unknown_error))
            }).let {
                compositeDisposable.add(it)
            }

    }

    private fun fetchIdFromUrl(url: String?): Int {
        if (url.isNullOrEmpty()) return -1
        val pattern: Pattern = Pattern.compile("(\\d+)(?!.*\\d)")
        val matcher: Matcher = pattern.matcher(url)
        if (matcher.find()) {
            return matcher.group(1)?.toInt() ?: -1
        }
        return -1
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}






