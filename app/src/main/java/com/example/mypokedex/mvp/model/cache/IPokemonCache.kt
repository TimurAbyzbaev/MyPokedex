package com.example.mypokedex.mvp.model.cache

import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IPokemonCache {
    fun getPokemons(): Single<List<PokemonFromResponse>>
    fun putPokemons(pokemons: List<PokemonFromResponse>) : Completable
}