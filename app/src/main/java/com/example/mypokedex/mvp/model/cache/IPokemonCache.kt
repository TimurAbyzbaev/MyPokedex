package com.example.mypokedex.mvp.model.cache

import com.example.mypokedex.mvp.model.entity.Pokemon
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IPokemonCache {
    fun getPokemons(): Single<List<Pokemon>>
    fun putPokemons(pokemons: List<Pokemon>) : Completable
}