package com.example.mypokedex.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.databinding.ListPokemonsBinding
import com.example.mypokedex.image.IImageLoader
import com.example.mypokedex.mvp.model.entity.PokemonFromResponse
import com.example.mypokedex.mvp.model.repo.IPokemonsRepo
import com.example.mypokedex.mvp.presenter.list.IPokemonListPresenter
import com.example.mypokedex.mvp.presenter.list.IPokemonsListPresenter
import com.example.mypokedex.mvp.view.list.PokemonItemView
import javax.inject.Inject

class PokemonRVAdapter(
    val presenter: IPokemonListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<PokemonRVAdapter.ViewHolder>() {

    @Inject
    lateinit var pokemonsRepo: IPokemonsRepo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ListPokemonsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ListPokemonsBinding) :
        RecyclerView.ViewHolder(vb.root), PokemonItemView {
        override fun setPokemonName(text: String) = with(vb) {
            tvPokemonName.text = text
        }

        override fun loadAvatar(pokemon: PokemonFromResponse) = with(vb) {
            imageLoader.loadInto(pokemon.imageUrl, vb.ivAvatar)
        }

        override var pos: Int = -1
    }
}