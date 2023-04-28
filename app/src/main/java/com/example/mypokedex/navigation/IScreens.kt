package com.example.mypokedex.navigation

import com.example.mypokedex.mvp.model.entity.Pokemon
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun pokemons(): Screen
    fun pokemon(pokemon: Pokemon): Screen

}