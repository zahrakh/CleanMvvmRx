package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zahra.catawiki.catawikiapp.domain.model.Chain
import com.zahra.catawiki.catawikiapp.domain.model.PokemonChainEvolution
import com.zahra.catawiki.catawikiapp.domain.model.Species

data class PokemonChainEvolutionDto(
    @SerializedName("chain")
    val chain: ChainDto?,
    @SerializedName("id")
    val id: Long?,
) {
    fun toChain(): PokemonChainEvolution {
        return PokemonChainEvolution(
            chain = chain?.toChain(),
            id = id
        )
    }
}

data class ChainDto(
    @SerializedName("species")
    val species: SpeciesDto?,
) {
    fun toChain(): Chain {
        return Chain(
            species = species?.toSpecies()
        )
    }
}

data class SpeciesDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
) {
    fun toSpecies(): Species {
        return Species(
            name = name,
            url = url,
        )
    }
}