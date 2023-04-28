package com.example.mypokedex

import android.app.Application
import com.example.mypokedex.dagger.AppComponent
import com.example.mypokedex.dagger.AppModule
import com.example.mypokedex.dagger.DaggerAppComponent
import com.example.mypokedex.dagger.pokemon.IPokemonScopeContainer
import com.example.mypokedex.dagger.pokemon.PokemonSubcomponent


class App : Application(), IPokemonScopeContainer {
    companion object {
        lateinit var instance: App
    }

    var pokemonSubcomponent: PokemonSubcomponent? = null
        private set

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initPokemonSubcomponent() = appComponent.pokemonSubcomponent().also {
        pokemonSubcomponent = it
    }

    override fun releasePokemonScope() {
        pokemonSubcomponent = null
    }
}