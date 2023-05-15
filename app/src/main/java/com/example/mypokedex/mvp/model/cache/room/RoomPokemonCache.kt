package com.example.mypokedex.mvp.model.cache.room

import com.example.mypokedex.mvp.model.cache.IPokemonCache
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.model.entity.room.dao.RoomPokemon
import com.example.mypokedex.mvp.model.repo.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomPokemonCache(private val db: Database) : IPokemonCache {
    override fun getPokemons(): Single<List<PokemonFromResponse>> =
        Single.fromCallable {
            db.pokemonDao.getAll().map { roomPokemon ->
                PokemonFromResponse(
                    //roomPokemon.url,
                    roomPokemon.name
                )
            }
        }

    override fun putPokemons(pokemons: List<PokemonFromResponse>): Completable = Completable.fromAction{
        val roomPokemons = pokemons.map { pokemon ->
            RoomPokemon(
                pokemon.name ?: "",
                pokemon.url ?: "",
            )
        }
        db.pokemonDao.insert(roomPokemons)
    }.subscribeOn(Schedulers.io())

}