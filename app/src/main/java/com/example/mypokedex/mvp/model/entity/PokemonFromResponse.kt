package com.example.mypokedex.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class ResponsePokemonsList(
    @Expose val results: List<PokemonFromResponse>
)
@Parcelize
data class PokemonFromResponse(
    @Expose val name: String? = null,
    @Expose val url: String? = null,
    var imageUrl: String? = null
) : Parcelable

data class Pokemon(
    @Expose val id: Int? = null,
    @Expose val sprites: Sprites? = null
)

data class Sprites(
    @Expose val front_default: String? = null
)
