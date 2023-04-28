package com.example.mypokedex.mvp.model.entity.room.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
class RoomPokemon(
    @PrimaryKey var gameIndex: Int,
    var name: String,
    @Expose val height: Double? = null,
    @Expose val weigth: Double? = null,
    @Expose val frontDefault: String? = null
)