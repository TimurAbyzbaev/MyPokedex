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
    var imageUrl: String? = null,
    var speciesUrl: String? = null
) : Parcelable

data class Pokemon(
    @Expose val id: Int? = null,
    @Expose val sprites: Sprites? = null,
    @Expose val species: Species? = null
)

data class Sprites(
    @Expose val frontDefault: String? = null
)

data class Species(
    @Expose val name: String? = null,
    @Expose val url: String? = null
)

data class PokemonSpecies(
    @Expose val flavorTextEntries: List<FlavorTextEntries>? = null
)

data class FlavorTextEntries(
    @Expose val flavorText: String? = null
)
