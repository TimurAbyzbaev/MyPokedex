package com.example.mypokedex.navigation

import com.example.mypokedex.ui.fragments.PokemonsFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun pokemons(): Screen = FragmentScreen { PokemonsFragment.newInstance()}


    override fun pokemon(): Screen = FragmentScreen {
        //PokemonFragment.newInstance()
        return@FragmentScreen
    }

}