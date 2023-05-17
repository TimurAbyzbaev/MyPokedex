package com.example.mypokedex.mvp.view

import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PokemonsView : MvpView {
    fun init()
    fun updateList()
    fun updatePokemonImage()

}