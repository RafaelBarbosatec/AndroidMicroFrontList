package rafaelbarbosatec.poc.listpokemon

import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import com.rafaelbarbosatec.sdk.data.pokemon.model.TypePokemon

interface PokemonListComunication{
    interface PokemonListListern{
        fun pokemonOnClick(pokemon: Pokemon)
    }

    interface PokemonListAction{
        fun filterByType(type: TypePokemon?)
    }
}