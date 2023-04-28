package com.example.mypokedex.mvp.model.cache.room

import com.example.mypokedex.mvp.model.cache.IPokemonCache
import com.example.mypokedex.mvp.model.entity.Pokemon
import com.example.mypokedex.mvp.model.entity.room.dao.RoomPokemon
import com.example.mypokedex.mvp.model.repo.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomPokemonCache(private val db: Database) : IPokemonCache {
    override fun getPokemons(): Single<List<Pokemon>> =
        Single.fromCallable {
            db.pokemonDao.getAll().map { roomPokemon ->
                Pokemon(
                    roomPokemon.gameIndex,
                    roomPokemon.name,
                    roomPokemon.height,
                    roomPokemon.weigth,
                    roomPokemon.frontDefault
                )
            }
        }

    override fun putPokemons(pokemons: List<Pokemon>): Completable = Completable.fromAction{
        val roomPokemons = pokemons.map { pokemon ->
            RoomPokemon(
                pokemon.gameIndex ?: 0,
                pokemon.name ?: "",
                pokemon.height ?: 0.0,
                pokemon.weigth ?: 0.0,
                pokemon.frontDefault ?: "",
            )
        }
        db.pokemonDao.insert(roomPokemons)
    }.subscribeOn(Schedulers.io())

}