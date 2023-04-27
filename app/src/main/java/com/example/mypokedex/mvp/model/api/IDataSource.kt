package com.example.mypokedex.mvp.model.api

import com.example.mypokedex.mvp.model.entity.Pokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("/pokemon")
    fun getPokemons(): Single<List<Pokemon>>
}