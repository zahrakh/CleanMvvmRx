package com.zahra.catawiki.catawikiapp.domain.usecase


import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class GetPokemonsUseCaseImplTest {

    private lateinit var usecase: GetPokemonsUseCase
    private lateinit var pokemonsRepository: PokemonsRepository

    @Before
    fun setUp() {
        pokemonsRepository = Mockito.mock(PokemonsRepository::class.java)
        usecase = GetPokemonsUseCaseDefault(pokemonsRepository)
    }

    @Test
    fun `test useCase must call pokemons repository`(): Unit = runBlocking {
        usecase(null)
        verify(pokemonsRepository, times(1)).getPokemonSpecies(null)
    }


}