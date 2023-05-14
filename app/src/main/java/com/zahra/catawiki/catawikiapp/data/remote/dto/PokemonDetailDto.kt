package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zahra.catawiki.catawikiapp.domain.model.PokemonDetails


data class PokemonDetailDto(
    @SerializedName("base_happiness")
    var baseHappiness: Int? = null,

    @SerializedName("capture_rate")
    var captureRate: Int? = null,

    @SerializedName("color")
    var color: ColorDto? = null,

    @SerializedName("egg_groups")
    var eggGroups: List<EggGroupDto>? = null,

    @SerializedName("evolution_chain")
    var evolutionChain: EvolutionChainDto? = null,

    @SerializedName("evolves_from_species")
    var evolvesFromSpecies: Any? = null,

    @SerializedName("flavor_text_entries")
    var flavorTextEntries: List<FlavorTextEntryDto>? = null,

    @SerializedName("form_descriptions")
    var formDescriptions: List<Any>? = null,

    @SerializedName("forms_switchable")
    var formsSwitchable: Boolean? = null,

    @SerializedName("gender_rate")
    var genderRate: Int? = null,

    @SerializedName("genera")
    var genera: List<GeneraDto>? = null,

    @SerializedName("generation")
    var generation: GenerationDto? = null,

    @SerializedName("growth_rate")
    var growthRate: GrowthRateDto? = null,

    @SerializedName("habitat")
    var habitat: HabitatDto? = null,

    @SerializedName("has_gender_differences")
    var hasGenderDifferences: Boolean? = null,

    @SerializedName("hatch_counter")
    var hatchCounter: Int? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("is_baby")
    var isBaby: Boolean? = null,

    @SerializedName("is_legendary")
    var isLegendary: Boolean? = null,

    @SerializedName("is_mythical")
    var isMythical: Boolean? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("names")
    var names: List<NameDto>? = null,

    @SerializedName("order")
    var order: Int? = null,

    @SerializedName("pal_park_encounters")
    var palParkEncounters: List<PalParkEncounterDto>? = null,

    @SerializedName("pokedex_numbers")
    var pokedexNumbers: List<PokedexNumberDto>? = null,

    @SerializedName("shape")
    var shape: ShapeDto? = null,

    @SerializedName("varieties")
    var varieties: List<VarietyDto>? = null
) {
    fun toPokemonDetails(): PokemonDetails {
        return PokemonDetails(
            this.name,
            this.flavorTextEntries.toString()
        )
    }
}

data class EggGroupDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class EvolutionChainDto(
    @SerializedName("url")
    var url: String? = null
)

data class NameDto(
    @SerializedName("language")
    var language: Language__2Dto? = null,

    @SerializedName("name")
    var name: String? = null
)

data class Language__2Dto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class FlavorTextEntryDto(
    @SerializedName("flavor_text")
    var flavorText: String? = null,

    @SerializedName("language")
    var language: LanguageDto? = null,

    @SerializedName("version")
    var version: VersionDto? = null
)

data class GeneraDto(
    @SerializedName("genus")
    var genus: String? = null,

    @SerializedName("language")
    var language: Language__1Dto? = null
)

data class GenerationDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class GrowthRateDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class HabitatDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class PalParkEncounterDto(
    @SerializedName("area")
    var area: AreaDto? = null,

    @SerializedName("base_score")
    var baseScore: Int? = null,

    @SerializedName("rate")
    var rate: Int? = null
)

data class PokedexNumberDto(
    @SerializedName("entry_number")
    var entryNumber: Int? = null,

    @SerializedName("pokedex")
    var pokedex: PokedexDto? = null
)

data class PokedexDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class VarietyDto(
    @SerializedName("is_default")
    var isDefault: Boolean? = null,

    @SerializedName("pokemon")
    var pokemon: PokemonDto? = null
)

data class VersionDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class Language__1Dto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class AreaDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class ColorDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class LanguageDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class ShapeDto(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)

