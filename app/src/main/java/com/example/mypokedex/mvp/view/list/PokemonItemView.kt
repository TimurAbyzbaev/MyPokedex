package com.example.mypokedex.mvp.view.list

interface PokemonItemView: IItemView {
    fun setPokemonName(text: String)
    fun loadAvatar(url: String)
}