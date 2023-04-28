package com.example.mypokedex.mvp.presenter.list

import com.example.mypokedex.mvp.view.list.IItemView
import com.example.mypokedex.mvp.view.list.PokemonItemView
import com.example.mypokedex.ui.adapters.PokemonsRVAdapter

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: PokemonsRVAdapter.ViewHolder)
    fun getCount() : Int
}

interface IPokemonsListPresenter : IListPresenter<PokemonItemView>
