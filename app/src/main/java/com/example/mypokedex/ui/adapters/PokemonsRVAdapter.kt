package com.example.mypokedex.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokedex.dagger.ImageLoadModule
import com.example.mypokedex.databinding.ListPokemonsBinding
import com.example.mypokedex.image.IImageLoader
import com.example.mypokedex.mvp.presenter.list.IPokemonsListPresenter
import com.example.mypokedex.mvp.view.list.PokemonItemView

class PokemonsRVAdapter(
    val presenter: IPokemonsListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<PokemonsRVAdapter.ViewHolder>() {

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

        override fun loadAvatar(url: String) = with(vb) {
            imageLoader.loadInto(url, vb.ivAvatar)
        }

        override var pos: Int = -1
    }
}