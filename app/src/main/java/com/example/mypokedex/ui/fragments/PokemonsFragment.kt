package com.example.mypokedex.ui.fragments

import com.example.mypokedex.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment

class PokemonsFragment: MvpAppCompatFragment(), BackButtonListener {

    companion object {
        fun newInstance() = PokemonsFragment()
    }

    val presenter:

    override fun backPressed(): Boolean = presenter.backPressed()
}