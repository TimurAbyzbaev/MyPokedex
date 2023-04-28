package com.example.mypokedex.mvp.model.repo.retrofit

import com.example.mypokedex.mvp.model.api.IDataSource
import com.example.mypokedex.mvp.model.cache.IPokemonCache
import com.example.mypokedex.mvp.model.entity.Pokemon
import com.example.mypokedex.mvp.model.repo.IPokemonsRepo
import com.example.mypokedex.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitPokemonsRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IPokemonCache
) : IPokemonsRepo {
    override fun getPokemons(): Single<List<Pokemon>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getPokemons().let {
                    it.flatMap { pokemons ->
                        cache.putPokemons(pokemons).toSingleDefault(pokemons)
                    }

                }.also {
                    var poceList = it
                }

            } else {
                cache.getPokemons()
            }.subscribeOn(Schedulers.io())
        }

}