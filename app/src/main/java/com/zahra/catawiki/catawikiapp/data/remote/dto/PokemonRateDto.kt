package com.zahra.catawiki.catawikiapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zahra.catawiki.catawikiapp.domain.model.PokemonRate

data class PokemonRateDto(
    @SerializedName("capture_rate")
    val captureRate: Long
) {
    fun toPokemonRate(): PokemonRate {
        return PokemonRate(
            captureRate = captureRate
        )
    }
}