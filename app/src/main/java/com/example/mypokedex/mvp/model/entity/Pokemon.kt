package com.example.mypokedex.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @Expose val gameIndex: Int? = null,
    @Expose val name: String? = null,
    @Expose val height: Double? = null,
    @Expose val weigth: Double? = null,
    @Expose val frontDefault: String? = null,
    @Expose val result: List<String>? = null
) : Parcelable