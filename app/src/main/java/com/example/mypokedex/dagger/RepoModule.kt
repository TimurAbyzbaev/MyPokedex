package com.example.mypokedex.dagger

import com.example.mypokedex.mvp.model.api.IDataSource
import com.example.mypokedex.mvp.model.repo.retrofit.RetrofitPokemonsRepo
import com.example.mypokedex.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun pokemonsRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IPokemonsCache) :
            IPokemonsRepo = RetrofitPokemonsRepo(api, networkStatus, cache)
}