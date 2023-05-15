package com.zahra.catawiki.catawikiapp.data.repository


import com.zahra.catawiki.catawikiapp.data.remote.NetworkDataSource
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonDto
import com.zahra.catawiki.catawikiapp.data.remote.dto.PokemonResponseDto
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class PokemonsRepositoryImplTest {

    private lateinit var networkDataSource: NetworkDataSource
    private lateinit var repository: PokemonsRepository


    @Before
    fun setUp() {
        networkDataSource = Mockito.mock(NetworkDataSource::class.java)
        repository = PokemonsRepositoryDefault(networkDataSource)
    }

    @Test
    fun `test get popular pokemon success`(): Unit = runBlocking {
        `when`(networkDataSource.getPokemonSpecies(pageUrl = null))
            .thenReturn(
                Single.just(
                    PokemonResponseDto(
                        count = 20,
                        next = null,
                        previous = null,
                        results = getPokemonDtoList(),
                    )
                )
            )

       repository.getPokemonSpecies(null).test().values()[0].results[0].name.equals("Name")
       repository.getPokemonSpecies(null).test().valueCount().equals(3)
     }

    private fun getPokemonDtoList(): List<PokemonDto> {
        return listOf(
            PokemonDto(
                name = "Name",
                url = "www.catawiki.com",
            ),PokemonDto(
                name = "Name",
                url = "www.catawiki.com",
            ),PokemonDto(
                name = "Name",
                url = "www.catawiki.com",
            )
        )
    }
}

