package com.example.mypokedex.dagger.pokemon.module

import com.example.mypokedex.App
import com.example.mypokedex.dagger.pokemon.IPokemonScopeContainer
import com.example.mypokedex.dagger.pokemon.PokemonScope
import com.example.mypokedex.mvp.model.api.IDataSource
import com.example.mypokedex.mvp.model.cache.IPokemonCache
import com.example.mypokedex.mvp.model.cache.room.RoomPokemonCache
import com.example.mypokedex.mvp.model.entity.room.dao.RoomPokemon
import com.example.mypokedex.mvp.model.repo.IPokemonsRepo
import com.example.mypokedex.mvp.model.repo.retrofit.RetrofitPokemonsRepo
import com.example.mypokedex.mvp.model.repo.room.Database
import com.example.mypokedex.mvp.network.AndroidNetworkStatus
import com.example.mypokedex.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class PokemonModule {
    @Provides
    fun pokemonsCache(database: Database) : IPokemonCache {
        return RoomPokemonCache(database)
    }

    @PokemonScope
    @Provides
    open fun pokemonRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IPokemonCache
    ): IPokemonsRepo {
        return RetrofitPokemonsRepo(api, networkStatus, cache)
    }

    @PokemonScope
    @Provides
    open fun scopeContainer(app: App) : IPokemonScopeContainer = app

}