package com.zahra.catawiki.catawikiapp.domain.model

data class PokemonChainEvolution(
    val chain: Chain?,
    val id: Long?,

    //imageUrl
    var ImageUrl: String? = null,
    //firstEvolutionSpecies rate
    var speciesRate: Long = 0
) {
    fun isPositiveRate(): Boolean = speciesRate > 0
}


data class Chain(
    val species: Species?,
)

data class Species(
    val name: String?,
    val url: String?,
)


