package com.example.mypokedex.dagger.pokemon

import com.example.mypokedex.dagger.pokemon.module.PokemonModule
import com.example.mypokedex.mvp.presenter.PokemonPresenter
import com.example.mypokedex.mvp.presenter.PokemonsPresenter
import dagger.Subcomponent

@PokemonScope
@Subcomponent(
    modules = [
        PokemonModule::class
    ]
)
interface PokemonSubcomponent {
    fun inject(pokemonsPresenter: PokemonsPresenter)
    fun inject(pokemonPresenter: PokemonPresenter)
}