package com.example.mypokedex.mvp.model.repo

import com.example.mypokedex.mvp.model.entity.Pokemon
import io.reactivex.rxjava3.core.Single

interface IPokemonsRepo {
    fun getPokemons() : Single<List<Pokemon>>
}