package com.example.mypokedex.navigation

import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.ui.fragments.PokemonFragment
import com.example.mypokedex.ui.fragments.PokemonsFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun pokemons(): Screen = FragmentScreen { PokemonsFragment.newInstance() }
    override fun pokemon(pokemonFromResponse: PokemonFromResponse): Screen = FragmentScreen { PokemonFragment.newInstance(pokemonFromResponse) }


}