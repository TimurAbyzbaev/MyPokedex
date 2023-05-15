package com.example.mypokedex.navigation

import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun pokemons(): Screen
    fun pokemon(pokemonFromResponse: PokemonFromResponse): Screen

}