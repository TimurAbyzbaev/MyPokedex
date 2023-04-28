package com.example.mypokedex.mvp.model.repo.room

import androidx.room.RoomDatabase
import com.example.mypokedex.mvp.model.entity.room.dao.PokemonDao
import com.example.mypokedex.mvp.model.entity.room.dao.RoomPokemon

@androidx.room.Database(
    entities = [
        RoomPokemon::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val pokemonDao: PokemonDao

    companion object {
        const val DB_NAME = "database.db"
    }
}