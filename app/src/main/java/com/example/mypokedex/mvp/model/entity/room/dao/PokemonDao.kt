package com.example.mypokedex.mvp.model.entity.room.dao

import androidx.room.*

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: RoomPokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg pokemons: RoomPokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemons: List<RoomPokemon>)

    @Update
    fun update(pokemon: RoomPokemon)

    @Update
    fun update(vararg pokemons: RoomPokemon)

    @Update
    fun update(pokemons: List<RoomPokemon>)

    @Delete
    fun delete(pokemon: RoomPokemon)

    @Delete
    fun delete(vararg pokemons: RoomPokemon)

    @Delete
    fun delete(pokemons: List<RoomPokemon>)

    @Query("SELECT * FROM RoomPokemon")
    fun getAll(): List<RoomPokemon>

    @Query("SELECT * FROM RoomPokemon WHERE name = :name LIMIT 1")
    fun findByName(name: String) : RoomPokemon?
}