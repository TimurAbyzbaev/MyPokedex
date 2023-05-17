package com.example.mypokedex.mvp.view.list

import com.example.mypokedex.mvp.model.entity.PokemonFromResponse

interface PokemonItemView: IItemView {
    fun setPokemonName(text: String)
    fun loadAvatar(pokemon: PokemonFromResponse)
}