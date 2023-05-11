package com.zahra.catawiki.catawikiapp.domain.model

data class PokemonResponse(
    val count: Long = 0,
    val next: String?,
    val results: List<Pokemon>,
)

data class Pokemon(
    val name: String?,
    val detailsUrl: String?,
 )