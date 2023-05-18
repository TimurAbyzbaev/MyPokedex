package com.example.mypokedex.mvp.model.repo.retrofit

import com.example.mypokedex.mvp.model.api.IDataSource
import com.example.mypokedex.mvp.model.cache.IPokemonCache
import com.example.mypokedex.mvp.model.entity.Pokemon
import com.example.mypokedex.mvp.model.entity.PokemonSpecies
import com.example.mypokedex.mvp.model.entity.ResponsePokemonsList
import com.example.mypokedex.mvp.model.entity.Species
import com.example.mypokedex.mvp.model.repo.IPokemonsRepo
import com.example.mypokedex.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single

class RetrofitPokemonsRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IPokemonCache
) : IPokemonsRepo {
    override fun getPokemons(): Single<ResponsePokemonsList> =
        api.getPokemons()

    override fun getPokemon(url: String): Single<Pokemon> =
        api.getPokemon(url)

    override fun getPokemonSpecies(url: String): Single<PokemonSpecies> =
        api.getPokemonSpecies(url)



    /*networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getPokemons().let {
                it.flatMap { pokemons ->
                    cache.putPokemons(pokemons).toSingleDefault(pokemons)
                }

            }.also {
                var poceList = it
            }

        } else {
            //cache.getPokemons()
            println("error")
        }.subscribeOn(Schedulers.io())
    }*/

}