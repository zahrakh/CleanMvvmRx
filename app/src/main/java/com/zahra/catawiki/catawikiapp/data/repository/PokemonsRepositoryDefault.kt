package com.zahra.catawiki.catawikiapp.data.repository

import com.zahra.catawiki.catawikiapp.data.remote.NetworkDataSource
import com.zahra.catawiki.catawikiapp.domain.repository.PokemonsRepository
import javax.inject.Inject

class PokemonsRepositoryDefault @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    ) : PokemonsRepository