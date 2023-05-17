package com.example.mypokedex.mvp.presenter

import android.annotation.SuppressLint
import com.example.mypokedex.dagger.pokemon.IPokemonScopeContainer
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.model.repo.IPokemonsRepo
import com.example.mypokedex.mvp.presenter.list.IPokemonsListPresenter
import com.example.mypokedex.mvp.view.PokemonsView
import com.example.mypokedex.mvp.view.list.PokemonItemView
import com.example.mypokedex.navigation.IScreens
import com.example.mypokedex.ui.adapters.PokemonsRVAdapter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonsPresenter(
    private val uiScheduler: Scheduler
) : MvpPresenter<PokemonsView>() {
    @Inject
    lateinit var pokemonsRepo: IPokemonsRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var pokemonScopeContainer: IPokemonScopeContainer

    class PokemonsListPresenter : IPokemonsListPresenter {
        val pokemons = mutableListOf<PokemonFromResponse>()
        override var itemClickListener: ((PokemonItemView) -> Unit)? = null
        override fun getCount(): Int = pokemons.size
        override fun bindView(view: PokemonsRVAdapter.ViewHolder) {
            val pokemon = pokemons[view.pos]
            pokemon.name?.let { view.setPokemonName(it) }
            pokemon.imageUrl?.let{ view.loadAvatar(pokemon)}

        }
    }

    val pokemonsListPresenter = PokemonsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        pokemonsListPresenter.itemClickListener = { itemView ->
            val pokemon = pokemonsListPresenter.pokemons[itemView.pos]
            router.navigateTo(screens.pokemon(pokemon))
        }
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        pokemonsRepo.getPokemons()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                pokemonsListPresenter.pokemons.clear()
                pokemonsListPresenter.pokemons.addAll(repos.results)
                viewState.updateList()
                loadPokemonImages()
                println("End loading sprites")
            }, {
                println(it.message)
            })
    }

    @SuppressLint("CheckResult")
    private fun loadPokemonImages() {
        pokemonsListPresenter.pokemons.forEach { pokemon ->
            pokemon.url?.let { pokemonUrl ->
                pokemonsRepo.getPokemon(pokemonUrl)
                    .observeOn(uiScheduler)
                    .subscribe({ pokemonResponse ->
                        val sprites = pokemonResponse.sprites
                        if (sprites != null) {
                            val imageUrl = sprites.front_default
                            println("pokemon " + pokemon.name + " " + imageUrl)
                            pokemon.imageUrl = imageUrl
                            viewState.updatePokemonImage()
                        }
                    }, {
                        println(it.message)
                    })
            }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        pokemonScopeContainer.releasePokemonScope()
        super.onDestroy()
    }

}