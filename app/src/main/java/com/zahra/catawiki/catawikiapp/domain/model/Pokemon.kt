package com.zahra.catawiki.catawikiapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Pokemons(
    val count: Int=0,
    val next: String?=null,
    val results: List<Pokemon> = arrayListOf(),
)

@Parcelize
data class Pokemon(
    var id:Int=-1,
    val name: String?,
    val detailsUrl: String?,
    val imageUrl:String?
 ):Parcelable