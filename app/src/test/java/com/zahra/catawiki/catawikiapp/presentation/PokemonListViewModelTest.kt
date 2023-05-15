package com.zahra.catawiki.catawikiapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zahra.catawiki.catawikiapp.data.remote.StringProvider
import com.zahra.catawiki.catawikiapp.domain.model.Pokemons
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonsUseCase
import com.zahra.catawiki.catawikiapp.domain.usecase.GetPokemonsUseCaseDefault
import com.zahra.catawiki.catawikiapp.presentation.explore.PokemonListViewModel
import com.zahra.catawiki.util.RxSchedulerRule
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class PokemonListViewModelTest {

    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private lateinit var useCase: GetPokemonsUseCase
    private lateinit var pokemonsRepository: PokemonsRepository
    private lateinit var stringProvider: StringProvider

    @Before
    fun setUp() {
        stringProvider = Mockito.mock(StringProvider::class.java)
        pokemonsRepository = Mockito.mock(PokemonsRepository::class.java)
        useCase = GetPokemonsUseCaseDefault(pokemonsRepository)
    }

    @Test
    fun `test get response from useCase and check nextUrl`() {
        `when`(useCase.invoke(null)).thenReturn(
            Single.just(
                Pokemons(
                    count = 1,
                    next = "www.pokemon.io/2",
                    results = arrayListOf(),
                )
            )
        )

        val viewModel = PokemonListViewModel(stringProvider, useCase)
        val count = viewModel.pokemonList.value?.count
        assertEquals(count, 1)
    }
}