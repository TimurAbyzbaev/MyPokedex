package com.example.mypokedex.mvp.view

import android.widget.ImageView
import com.example.mypokedex.image.IImageLoader
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PokemonsView : MvpView {
    fun init()
    fun updateList()
    fun setAvatar(imageLoader: IImageLoader<ImageView>, url: String)
    fun setAvatar()

    fun setName(pokemonFromResponse: PokemonFromResponse)
    fun setPokemonSpecies(pokemonSpecies: String?)

}