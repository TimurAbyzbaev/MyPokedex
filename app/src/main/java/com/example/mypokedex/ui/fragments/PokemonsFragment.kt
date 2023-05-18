package com.example.mypokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypokedex.App
import com.example.mypokedex.dagger.pokemon.PokemonSubcomponent
import com.example.mypokedex.databinding.FragmentPokemonsBinding
import com.example.mypokedex.image.GlideImageLoader
import com.example.mypokedex.image.IImageLoader
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.presenter.PokemonsPresenter
import com.example.mypokedex.mvp.view.PokemonsView
import com.example.mypokedex.ui.activity.BackButtonListener
import com.example.mypokedex.ui.adapters.PokemonsRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PokemonsFragment: MvpAppCompatFragment(), PokemonsView, BackButtonListener {

    companion object {
        fun newInstance() = PokemonsFragment()
    }

    var pokemonSubComponent: PokemonSubcomponent? = null

    val presenter: PokemonsPresenter by moxyPresenter {
        PokemonsPresenter(AndroidSchedulers.mainThread()).apply {
            pokemonSubComponent = App.instance.initPokemonSubcomponent()
            pokemonSubComponent?.inject(this)
        }
    }

    var adapter: PokemonsRVAdapter? = null
    private var vb: FragmentPokemonsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentPokemonsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean = presenter.backPressed()
    override fun init() {
        vb?.rvPokemons?.layoutManager = LinearLayoutManager(context)
        adapter = PokemonsRVAdapter(presenter.pokemonsListPresenter, GlideImageLoader())
        vb?.rvPokemons?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setAvatar(imageLoader: IImageLoader<ImageView>, url: String) {
        updateList()
    }

    override fun setAvatar() {

    }

    override fun setName(pokemonFromResponse: PokemonFromResponse) {
        //
    }

}