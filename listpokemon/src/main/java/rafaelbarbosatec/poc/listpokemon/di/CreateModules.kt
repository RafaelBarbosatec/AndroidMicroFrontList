package rafaelbarbosatec.poc.listpokemon.di

import com.rafaelbarbosatec.sdk.RepositorySDK
import com.rafaelbarbosatec.sdk.data.pokemon.repository.PokemonRepository

object CreateModules {

    fun pokemonRepository(sdk:RepositorySDK): PokemonRepository{
        return sdk.pokemon()
    }

}