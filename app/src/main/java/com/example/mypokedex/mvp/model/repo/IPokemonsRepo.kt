package com.example.mypokedex.mvp.model.repo

import com.example.mypokedex.mvp.model.entity.Pokemon
import com.example.mypokedex.mvp.model.entity.ResponsePokemonsList
import io.reactivex.rxjava3.core.Single

interface IPokemonsRepo {
    fun getPokemons() : Single<ResponsePokemonsList>
    fun getPokemon(url: String) : Single<Pokemon>
}