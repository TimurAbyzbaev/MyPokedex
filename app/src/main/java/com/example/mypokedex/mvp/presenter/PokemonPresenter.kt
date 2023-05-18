package com.example.mypokedex.mvp.presenter

import android.annotation.SuppressLint
import android.widget.ImageView
import com.example.mypokedex.dagger.pokemon.IPokemonScopeContainer
import com.example.mypokedex.image.IImageLoader
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.model.repo.IPokemonsRepo
import com.example.mypokedex.mvp.presenter.list.IPokemonsListPresenter
import com.example.mypokedex.mvp.view.PokemonsView
import com.example.mypokedex.mvp.view.list.PokemonItemView
import com.example.mypokedex.navigation.IScreens
import com.example.mypokedex.ui.adapters.PokemonRVAdapter
import com.example.mypokedex.ui.adapters.PokemonsRVAdapter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonPresenter(
    private val pokemon: PokemonFromResponse,
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

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    class PokemonsListPresenter : IPokemonsListPresenter {
        val pokemons = mutableListOf<PokemonFromResponse>()
        override var itemClickListener: ((PokemonItemView) -> Unit)? = null
        override fun getCount(): Int = pokemons.size
        override fun bindView(view: PokemonsRVAdapter.ViewHolder) {


        }

        override fun bindView(view: PokemonRVAdapter.ViewHolder) {
            val pokemon = pokemons[view.pos]
            pokemon.name?.let { view.setPokemonName(it) }
            pokemon.imageUrl?.let{ view.loadAvatar(pokemon)}
        }
    }

    val pokemonsListPresenter = PokemonsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadPokemonDescription()
        pokemon.imageUrl.let {
            if (it != null) {
                viewState.setAvatar(imageLoader, it)
            }
        }
        pokemon.let { viewState.setName(it) }
    }
    @SuppressLint("CheckResult")
    private fun loadPokemonDescription(){
        pokemon.speciesUrl?.let {
            pokemonsRepo.getPokemonSpecies(it)
                .observeOn(uiScheduler)
                .subscribe({ speciesResponse ->
                    val species = speciesResponse.flavorTextEntries
                    if(species != null){
                        val pokemonSpecies = species[0].flavorText
                        println(pokemon.name + " descr: " + pokemonSpecies)
                        viewState.setPokemonSpecies(pokemonSpecies)
                    }
                }, {
                    println(it.message)
                })
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