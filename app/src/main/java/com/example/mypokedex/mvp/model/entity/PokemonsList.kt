package com.example.mypokedex.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonsList(
    @Expose val result: List<PokemonFromResponse>? = null
) : Parcelable