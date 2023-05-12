package com.zahra.catawiki.catawikiapp.domain.model

data class Pokemons(
    val count: Int=0,
    val next: String?=null,
    val results: List<Pokemon> = arrayListOf(),
)

data class Pokemon(
    val name: String?,
    val detailsUrl: String?,
    val imageUrl:String?
 )