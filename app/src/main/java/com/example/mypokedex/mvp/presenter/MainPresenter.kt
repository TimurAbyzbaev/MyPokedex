package com.example.mypokedex.mvp.presenter

import com.example.mypokedex.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView
import javax.inject.Inject

class MainPresenter : MvpPresenter<MvpView>() {
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.pokemons())
    }

    fun backClicked() {
        router.exit()
    }
}