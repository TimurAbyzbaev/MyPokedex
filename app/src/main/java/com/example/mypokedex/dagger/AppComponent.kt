package com.example.mypokedex.dagger

import com.example.mypokedex.dagger.pokemon.PokemonSubcomponent
import com.example.mypokedex.mvp.presenter.MainPresenter
import com.example.mypokedex.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        ImageLoadModule::class
    ]
)
interface AppComponent {
    fun pokemonSubcomponent() : PokemonSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}