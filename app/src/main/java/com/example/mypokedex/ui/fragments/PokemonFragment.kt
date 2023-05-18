package com.example.mypokedex.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypokedex.App
import com.example.mypokedex.databinding.FragmentPokemonBinding
import com.example.mypokedex.image.IImageLoader
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.presenter.PokemonPresenter
import com.example.mypokedex.mvp.view.PokemonsView
import com.example.mypokedex.ui.activity.BackButtonListener
import com.example.mypokedex.ui.adapters.PokemonRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PokemonFragment: MvpAppCompatFragment(), PokemonsView, BackButtonListener {

    companion object {
        private const val POKEMON_ARG = "pokemon"
        fun newInstance(pokemon: PokemonFromResponse) = PokemonFragment().apply {
            arguments = Bundle().apply {
                putParcelable(POKEMON_ARG, pokemon)
            }
        }
    }

    val presenter: PokemonPresenter by moxyPresenter {
        val pokemon = arguments?.getParcelable<PokemonFromResponse>(POKEMON_ARG) as PokemonFromResponse

        PokemonPresenter(pokemon, AndroidSchedulers.mainThread()).apply {
            App.instance.initPokemonSubcomponent().inject(this)
        }
    }

    private var _binding: FragmentPokemonBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: PokemonRVAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentPokemonBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvAbilities.layoutManager = LinearLayoutManager(context)
        //adapter = PokemonRVAdapter(presenter.pokemonsAbilitiesListPresenter)
        binding.rvAbilities.adapter = adapter
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setAvatar(imageLoader: IImageLoader<ImageView>, url: String) {
        imageLoader.loadInto(url, binding.ivAvatar)
    }

    override fun setAvatar() {

    }

    override fun setName(pokemonFromResponse: PokemonFromResponse) {
        binding.textViewName.text = pokemonFromResponse.name
    }

    override fun setPokemonSpecies(pokemonSpecies: String?) {
        binding.tvDescription.text = pokemonSpecies
    }


    override fun backPressed() = presenter.backPressed()
}