package com.example.mypokedex.mvp.model.api

import com.example.mypokedex.mvp.model.entity.Pokemon
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.model.entity.ResponsePokemonsList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("pokemon")
    fun getPokemons(): Single<ResponsePokemonsList>

    @GET
    fun getPokemon(@Url url: String): Single<Pokemon>
}