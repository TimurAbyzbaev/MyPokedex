package com.example.mypokedex.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @Expose val game_index: Int? = null,
    @Expose val height: Double? = null,
    @Expose val weigth: Double? = null,
    @Expose val frontDefault: Double? = null
) : Parcelable